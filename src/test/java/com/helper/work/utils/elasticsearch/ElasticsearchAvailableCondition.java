package com.helper.work.utils.elasticsearch;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * {@link ExecutionCondition} evaluating {@link EnabledOnElasticsearch @EnableOnElasticsearch}.
 *
 * @author Christoph Strobl
 * @author Prakhar Gupta
 */
public class ElasticsearchAvailableCondition implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {

        var annotation = AnnotationSupport.findAnnotation(extensionContext.getElement(),
                EnabledOnElasticsearch.class);

        return annotation.map(EnabledOnElasticsearch::url)
                .map(ElasticsearchAvailableCondition::checkServerRunning)
                .orElse(ConditionEvaluationResult.enabled("Enabled by default"));
    }

    private static ConditionEvaluationResult checkServerRunning(String url) {

        var template = new RestTemplate();

        try {
            var entity = template.exchange(url, HttpMethod.HEAD, null, byte[].class);
            return ConditionEvaluationResult.enabled("Successfully connected to elasticsearch server. " + entity.getStatusCode());
        } catch (RuntimeException e) {
            return ConditionEvaluationResult.disabled("Elasticsearch unavailable at " + url + "; " + e.getMessage());
        }
    }
}
