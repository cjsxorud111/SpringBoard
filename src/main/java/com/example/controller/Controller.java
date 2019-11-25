package com.example.controller;

import com.example.service.DefineService;
import com.example.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private DefineService defineService;

    private MemberService memberService;

    @Autowired
    public void service(DefineService defineService) {
        this.defineService = defineService;
    }

    @Autowired
    public void memberService(MemberService memberService) { this.memberService = memberService; }

    @RequestMapping(value = "/idValidCheck", produces = "application/text; charset=utf8", method = RequestMethod.POST)
    public String idValidCheck(HttpServletRequest request) throws Exception {
        String inputId = request.getParameter("id");
        String result = null;
        try {
            result = memberService.idValidCheck(inputId);
        } catch (Exception e){
            e.printStackTrace();
        }
        if (result == null) {
            return "possibleId";
        } else {
            return "duplicateId";
        }
    }

    @RequestMapping(value = "/searchWord", produces = "application/text; charset=utf8", method = RequestMethod.POST)
    public String searchWord(HttpServletRequest request) {
        String result = null;
        try {
            result = defineService.searchWord(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/modifyWriting", method = RequestMethod.POST)
	public String modifyWriting(HttpServletRequest request) {
		try {
            defineService.modifyWriting(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}