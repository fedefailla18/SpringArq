package com.helper.work.utils;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GoogleCalendarServiceTest {

    @InjectMocks
    private GoogleCalendarService googleCalendarService;

    @AfterEach
    void tearDown() {
    }

    @Test
    void createGoogleService() {
        // Build a new authorized API client service.
        Calendar service = null;
        List<CalendarListEntry> items = null;
        try {
            service = googleCalendarService.createCalendar();
            final Calendar.CalendarList calendars = service.calendarList();
            final CalendarList execute = calendars.list()
                    .execute();
            items = execute.getItems();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        assertNotNull(service);
        assertNotNull(items);
    }
}