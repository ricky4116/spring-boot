package com.apple.shop;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {
    @GetMapping("/")
    String hello(){
        return "index.html";
    }

    @GetMapping("/about")
    @ResponseBody
    String about(){
        return "그냥사이트에요";
    }
    @GetMapping("/date")
    @ResponseBody
    String date(){
        return ZonedDateTime.now().toString();
    }
}
