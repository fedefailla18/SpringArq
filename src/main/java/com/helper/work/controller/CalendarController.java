package com.helper.work.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    @GetMapping("/hello")
    public String getCalendar() {
        return "hello";
    }
}
