package com.joebrooks.whoareyou.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joebrooks.whoareyou.Common.Response.ResponseCode;
import com.joebrooks.whoareyou.DTO.Response;
import com.joebrooks.whoareyou.Service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/devices")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping("/{userId}")
    public Response getDevices(@PathVariable("userId") String userId) throws JsonProcessingException {
        String json = deviceService.getAllDevices(userId);

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
