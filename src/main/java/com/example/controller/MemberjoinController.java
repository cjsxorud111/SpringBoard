package com.example.controller;

import com.example.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MemberjoinController {

    @Inject
    private MemberService service;

    @RequestMapping(value = "/memberjoin", method = RequestMethod.GET)
    public String memberjoin() throws Exception {
        return "memberjoin";
    }

    @RequestMapping(value = "/memberjoining", method = RequestMethod.GET)
    public String memberjoining(HttpServletRequest request) throws Exception {
        service.memberjoining(request);
        return "redirect:/";
    }
}
