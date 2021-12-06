package com.helper.work.utils.elasticsearch;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;

/**
 * {@code @EnableOnElasticsearch} is used to signal that the annotated test class or test method is only
 * <em>enabled</em> if Elasticsearch is available at {@link #url()}.
 * <p>
 * When applied at the class level, all test methods within that class will be enabled if Elasticsearch is available.
 * <p>
 * If a test method is disabled via this annotation, that does not prevent the test class from being instantiated.
 * Rather, it prevents the execution of the test method and method-level lifecycle callbacks such as {@code @BeforeEach}
 * methods, {@code @AfterEach} methods, and corresponding extension APIs.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@ExtendWith(ElasticsearchAvailableCondition.class)
public @interface EnabledOnElasticsearch {

    /**
     * URL pointing at the Elasticsearch instance to check.
     */
    String url() default "http://localhost:9200";

}