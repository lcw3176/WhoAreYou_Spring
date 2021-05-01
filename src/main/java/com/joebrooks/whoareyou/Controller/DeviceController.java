package com.joebrooks.whoareyou.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joebrooks.whoareyou.Common.Response.ResponseCode;
import com.joebrooks.whoareyou.DTO.Response;
import com.joebrooks.whoareyou.Service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/devices")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping("/{email}")
    public Response getDevices(@PathVariable("email") String email) throws JsonProcessingException {
        String json = deviceService.getAllDevices(email);

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


    @PostMapping
    public Response addDevice(@RequestParam("email") String userId, @RequestParam("name") String deviceName){
        if(deviceService.addDeviceByEmailAndDeviceName(userId, deviceName)){
            return Response.builder()
                    .code(ResponseCode.success)
                    .build();
        }

        return Response.builder()
                .code(ResponseCode.wrongRequest)
                .build();
    }

    @DeleteMapping("/{email}/{deviceName}")
    public Response deleteDevice(@PathVariable("email") String email, @PathVariable("deviceName") String deviceName){
        
        if(deviceService.deleteDeviceByEmailAndDeviceName(email, deviceName)){
            return Response.builder()
                    .code(ResponseCode.success)
                    .build();
        }

        return Response.builder()
                .code(ResponseCode.wrongRequest)
                .build();
    }
}
