package com.letter.controllers;

import com.letter.tcp.JsonGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = {"/tcp"})
public class TCPController {


    private static final Logger log = LoggerFactory.getLogger(TCPController.class);

    @Autowired
    private JsonGateway gw;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView("tcp/list", model);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get() {
        Map<String, Object> model = new HashMap<>();
        gw.send("you bitch");
        return new ModelAndView("tcp/list", model);
    }

}
