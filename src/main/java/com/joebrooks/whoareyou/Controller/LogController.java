package com.joebrooks.whoareyou.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/log")
@RequiredArgsConstructor
public class LogController {

    @GetMapping
    public String hello(){
        return "HELLO";
    }

}
