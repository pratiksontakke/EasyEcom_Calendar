package com.easyEcom.service;

import com.easyEcom.model.EventDTO;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
public class CalendarService {

    private final static Log logger = LogFactory.getLog(CalendarService.class);
    private static final String APPLICATION_NAME = "EasyEcom_Calendar";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static HttpTransport httpTransport;
    private static com.google.api.services.calendar.Calendar client;

    GoogleClientSecrets clientSecrets;
    GoogleAuthorizationCodeFlow flow;
    Credential credential;
    TokenResponse response;
    String calendarId = "primary";
    @Value("${google.client.client-id}")
    private String clientId;
    @Value("${google.client.client-secret}")
    private String clientSecret;
    @Value("${google.client.redirectUri}")
    private String redirectURI;

    public void initializeClient() throws GeneralSecurityException, IOException {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        credential = flow.createAndStoreCredential(response, "Pratik Sontakke");
        client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {

        return new RedirectView(authorize());
    }

    public String oauth2Callback(@RequestParam(value = "code") String code) {
        com.google.api.services.calendar.model.Events eventList;
        String message;
        try {
            response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
            response.setExpiresInSeconds(18000L);

            message = "Created Access token";
            initializeClient();

        } catch (Exception e) {
            logger.warn("Exception while handling OAuth2 callback (" + e.getMessage() + ")."
                    + " Redirecting to google connection status page.");
            message = "Exception while handling OAuth2 callback (" + e.getMessage() + ")."
                    + " Redirecting to google connection status page.";
        }
        return message;
    }

    private String authorize() throws Exception {
        AuthorizationCodeRequestUrl authorizationUrl;

        if (flow == null) {
            Details web = new Details();
            web.setClientId(clientId);
            web.setClientSecret(clientSecret);
            clientSecrets = new GoogleClientSecrets().setWeb(web);
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
                    Collections.singleton(CalendarScopes.CALENDAR))
                    .setAccessType("offline")
                    .setApprovalPrompt("force")
                    .build();
        }
        authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURI);

        System.out.println("cal authorizationUrl->" + authorizationUrl);
        return authorizationUrl.build();
    }


    public Event createCalenderEvent(EventDTO e) {
        Event event = new Event()
                .setSummary(e.getSummary())
                .setLocation(e.getLocation())
                .setDescription(e.getDescription());
        ConferenceSolutionKey conferenceSKey = new ConferenceSolutionKey();
        conferenceSKey.setType("hangoutsMeet");
        CreateConferenceRequest createConferenceReq = new CreateConferenceRequest();
        createConferenceReq.setRequestId(UUID.randomUUID().toString()); // ID

        createConferenceReq.setConferenceSolutionKey(conferenceSKey);
        ConferenceData conferenceData = new ConferenceData();
        conferenceData.setCreateRequest(createConferenceReq);

        System.out.println(conferenceData);
        event.setConferenceData(conferenceData);

        DateTime startDateTime = new DateTime(e.getStartTime());
        System.out.println(startDateTime);
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("IST");
        event.setStart(start);

        DateTime endDateTime = new DateTime(e.getEndTime());
        System.out.println(endDateTime);
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("IST");
        event.setEnd(end);

        String[] recurrence = new String[]{}; // you can add recurrence rule here -> "RRULE:FREQ=DAILY;COUNT=2"
        event.setRecurrence(Arrays.asList(recurrence));

        EventAttendee[] attendees = new EventAttendee[e.getAttendees().length];
        for (int i = 0; i < e.getAttendees().length; i++) {
            attendees[i] = new EventAttendee().setEmail(e.getAttendees()[i]);
        }

        event.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(5),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);
        event.getHangoutLink();
        return event;
    }

    public List<Event> getCalendarEvents(String startDate, String endDate, String calendarId1) throws IOException {
        CalendarList calendarList = client.calendarList().list().execute();
        List<CalendarListEntry> items = calendarList.getItems();

        List<Event> events = client.events().list(calendarId)
                .setTimeMin(new DateTime(startDate))
                .setTimeMax(new DateTime(endDate))
                .setSingleEvents(true)
                .execute()
                .getItems();

        return events;
    }

    public String scheduleGoogleMeeting(EventDTO e) throws GeneralSecurityException, IOException {
        initializeClient();

        calendarId = "primary";
        Event event = getEvent(e.getStartTime(), e.getEndTime());

        if (event == null) {
            // Schedule a new event if no event with the same start time and end time exists
            client.events().insert(calendarId, createCalenderEvent(e))
                    .setConferenceDataVersion(1)
                    .setSendNotifications(true).execute();
            return "Created Calendar Event";
        } else {
            return "Event already exists";
        }


    }

    private Event getEvent(String startTime, String endTime) throws IOException {
        // Search for events with the same start time and end time
        Calendar calendar = client.calendars().get(calendarId).execute();
        DateTime timeMin = new DateTime(startTime);
        DateTime timeMax = new DateTime(endTime);
        List<Event> events = client.events().list(calendarId)
                .setTimeMin(timeMin)
                .setTimeMax(timeMax)
                .execute()
                .getItems();

        if (events.isEmpty()) {
            // Return null if no event with the same start time and end time is found
            return null;
        } else {
            // Return the first event found
            return events.get(0);
        }
    }


/*

    public boolean isCalendarIdExits(String calendarId1) throws IOException {
        CalendarList calendarList = client.calendarList().list().execute();
        List<CalendarListEntry> items = calendarList.getItems();
        for (CalendarListEntry calendarListEntry : items) {
            System.out.println(calendarListEntry.getSummary());
            if (calendarListEntry.getSummary().equals("calendarId1")) {
                return true;
            }
        }
        return true;
    }

*/



}