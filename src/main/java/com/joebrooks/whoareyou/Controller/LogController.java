package com.joebrooks.whoareyou.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joebrooks.whoareyou.Service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/logs")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    @GetMapping("/{userId}/{deviceName}")
    public ResponseEntity getLogs(@PathVariable("deviceName") String deviceName, @PathVariable("userId") String userId) throws JsonProcessingException {
        String json = logService.getLogs(deviceName, userId);

        if(json != null){
            return ResponseEntity.ok().body(json);
        }

        return ResponseEntity.notFound().build();
    }

}
