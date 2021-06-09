package com.joebrooks.whoareyou.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joebrooks.whoareyou.Service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/devices")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping("/{email}")
    public ResponseEntity getDevices(@PathVariable("email") String email) throws JsonProcessingException {
        String json = deviceService.getAllDevices(email);

        if(json != null){
            return ResponseEntity.ok().body(json);
        }

        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/{email}/{deviceName}")
    public ResponseEntity deleteDevice(@PathVariable("email") String email, @PathVariable("deviceName") String deviceName){
        
        if(deviceService.deleteDeviceByEmailAndDeviceName(email, deviceName)){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
