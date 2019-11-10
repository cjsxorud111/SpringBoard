package com.example.controller;

import com.example.service.DefineService;
import com.sun.tools.internal.xjc.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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