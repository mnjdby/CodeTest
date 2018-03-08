package com.fullerton.olp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Starter controller loads the index.jsp
 * to start the application correctly
 */
@Controller
public class StarterController {

    @RequestMapping("/")
    public String loadIndex(){
        return "index";
    }

}
