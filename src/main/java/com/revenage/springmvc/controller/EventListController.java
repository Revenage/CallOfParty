package com.revenage.springmvc.controller;

import com.revenage.springmvc.model.UserEvent;
import com.revenage.springmvc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by revenage on 8/14/16.
 */

@Controller
@RequestMapping("/")
public class EventListController {

    @Autowired
    EventService eventService;

    @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
    public String listUserEvents(ModelMap model) {
        List<UserEvent> userEvents = eventService.findAllUserEvents();
        model.addAttribute("userEvents", userEvents);
        return "alluserevents";
    }
}
