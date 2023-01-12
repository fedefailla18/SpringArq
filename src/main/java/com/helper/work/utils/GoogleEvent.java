package com.helper.work.utils;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GoogleEvent {

    private String summary;
    private Date startDate;
    private Date endDate;
    private EventAttendee[] attendees;

    public static Event newEvent() {
        Event event = new Event();
        event.setSummary("New Event");
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000);
        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
        event.setStart(new EventDateTime().setDateTime(start));
        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
        event.setEnd(new EventDateTime().setDateTime(end));
        EventAttendee[] attendees = new EventAttendee[]{
                new EventAttendee().setEmail("federico.failla@distillery.com"),};
        event.setAttendees(Arrays.asList(attendees));
        return event;
    }

    private static void eventDates( ) {
        final Event event = new Event()
                .setSummary("Google I/O 2015");
        DateTime startDateTime = new DateTime("2015-05-28T09:00:00-07:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setStart(start);

        DateTime endDateTime = new DateTime("2015-05-28T17:00:00-07:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setEnd(end);
    }

    public static void listEvents(Calendar service) throws IOException {
        // List the next 10 events from the primary calendar.
        List<Event> items = getEvents(service);
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event ev : items) {
                DateTime startTime = ev.getStart().getDateTime();
                if (startTime == null) {
                    startTime = ev.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", ev.getSummary(), startTime);
            }
        }
    }

    private static List<Event> getEvents(Calendar service) throws IOException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        return items;
    }

    public static void createEvent(Calendar service) throws IOException {
        String calendarId = "primary";
        Event event = newEvent();
        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());
    }
}
