package com.fullerton.olp.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController{

    private static final String PATH = "/index";

    @RequestMapping(value = PATH)
    public String error() {
        return "index";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}