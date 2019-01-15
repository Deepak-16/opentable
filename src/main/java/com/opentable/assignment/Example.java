package com.opentable.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;

public class Example {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

}
