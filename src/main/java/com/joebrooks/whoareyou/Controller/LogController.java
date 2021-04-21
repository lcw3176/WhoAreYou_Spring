package com.joebrooks.whoareyou.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joebrooks.whoareyou.Common.Response.ResponseCode;
import com.joebrooks.whoareyou.DTO.Response;
import com.joebrooks.whoareyou.Service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/logs")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    @GetMapping("/{deviceName}")
    public Response getLogs(@PathVariable("deviceName") String deviceName) throws JsonProcessingException {

        String json = logService.getLogs(deviceName);

        if(json != null){
            return Response.builder()
                    .code(ResponseCode.success)
                    .result(json)
                    .build();
        }

        return Response.builder()
                .code(ResponseCode.notFound)
                .build();
    }

}
