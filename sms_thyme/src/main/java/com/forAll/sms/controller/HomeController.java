package com.forAll.sms.controller;

import com.forAll.sms.dto.ParentsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {


    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav =  new ModelAndView("home");
        return mav;
    }
}
