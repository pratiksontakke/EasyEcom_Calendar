package com.easyEcom.controller;

import com.easyEcom.model.EventDTO;
import com.easyEcom.service.CalendarService;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@RestController()
public class CalendarController {

    @Autowired
    CalendarService cSer;

    @GetMapping("/login/google")
    public RedirectView googleConnectionStatusHandler(HttpServletRequest request) throws Exception {
        return cSer.googleConnectionStatus(request);
    }

    @GetMapping(value = "/login/google", params = "code")
    public ResponseEntity<String> oauth2Callback(@RequestParam("code") String code) {
        String msg = cSer.oauth2Callback(code);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


    @GetMapping("/getEvents")
    public ResponseEntity<List<Event>> getCalendarEvents(
            @RequestParam(value = "startDate", required = true) String startDate,
            @RequestParam(value = "endDate", required = true) String endDate,
            @RequestParam(value = "calendarId1") String calendarId) throws IOException {

        List<Event> events = cSer.getCalendarEvents(startDate, endDate, calendarId);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/addEvent")
    public ResponseEntity<String> scheduleGoogleMeeting(@RequestBody EventDTO e) throws IOException {

        String msg = cSer.scheduleGoogleMeeting(e);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);

    }

}




