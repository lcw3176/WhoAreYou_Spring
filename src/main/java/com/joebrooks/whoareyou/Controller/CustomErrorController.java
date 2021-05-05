package com.joebrooks.whoareyou.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public ResponseEntity error(){

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/error/noAuth")
    public ResponseEntity noAuth(){

        return ResponseEntity.badRequest().build();
    }

    // Deprecated
    @Override
    public String getErrorPath() {
        return null;
    }
}
