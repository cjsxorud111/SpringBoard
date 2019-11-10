package com.example.controller;

import com.example.service.DefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {

    private DefineService service;

    @Autowired
    public void service(DefineService service) {
        this.service = service;
    }

    @RequestMapping(value = "/searchWord", produces = "application/text; charset=utf8", method = RequestMethod.POST)
    public String searchWord(HttpServletRequest request) {

        String result = null;
        try {
            result = service.searchWord(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}