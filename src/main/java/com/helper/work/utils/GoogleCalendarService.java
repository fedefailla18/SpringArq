package com.helper.work.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/**
 * Connect to Google Service. It makes log in and get a Calendar service which we can use
 * to hit Google Calendar API.
 */
@Service
public class GoogleCalendarService {
    /** Application name. */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

    private static final String USER_ID_KEY = "MY_DUMMY_USER";

    private NetHttpTransport httpTransport;
    private GoogleAuthorizationCodeFlow flow;
    private Credential user;

    /** Directory to store authorization tokens for this application. */
    @Value("${google.tokens.directory-path}")
    private static String tokensDirectoryPath = "tokens";

    @Value("${google.auth.callback-uri}")
    private static final String CALLBACK_URI = "http://localhost:8080/";

    @Value("${google.credentials.file-path}")
    private static String credentialsFilePath = "client_secret.json";

    private void initializeFlow() throws IOException, GeneralSecurityException {
        // Load client secrets.
        InputStream in = GoogleCalendarService.class.getResourceAsStream(credentialsFilePath);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + credentialsFilePath);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        // Build flow and trigger user authorization request.
        flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(tokensDirectoryPath)))
                .setAccessType("offline")
                .build();
    }

    /**
     * Creates an authorized Credential object.
     * ex param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private Credential getCredentials() throws IOException, GeneralSecurityException {
        initializeFlow();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //returns an authorized Credential object.
        user = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return user;
    }

    public Calendar createCalendar() throws IOException, GeneralSecurityException {
        final Credential credentials = getCredentials();
        return new Calendar.Builder(httpTransport, JSON_FACTORY, credentials)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public void saveAuthCode(String code) throws IOException {
        GoogleTokenResponse response = flow.newTokenRequest(code)
                .setRedirectUri(CALLBACK_URI)
                .execute();
        flow.createAndStoreCredential(response, USER_ID_KEY);
    }

    public void getUserCredentials() throws IOException {
        final Credential credential = flow.loadCredential(USER_ID_KEY);
    }
}