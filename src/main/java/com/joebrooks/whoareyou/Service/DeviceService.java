package com.joebrooks.whoareyou.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.joebrooks.whoareyou.DTO.Device;
import com.joebrooks.whoareyou.DTO.Log;
import com.joebrooks.whoareyou.Entity.UserEntity;
import com.joebrooks.whoareyou.Repository.LogRepository;
import com.joebrooks.whoareyou.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final LogRepository logRepository;
    private final UserRepository userRepository;

    public String getAllDevices(String email) throws JsonProcessingException {
        UserEntity user = userRepository.findByEmail(email);
        List<Device> devices = new ArrayList<>();

        for(var i : logRepository.findAllByUserIdx(user.getIdx())){
            devices.add(Device.builder()
                    .name(i.getDeviceName())
                    .time(i.getTime())
                    .build());
        }

        if (devices.size() != 0) {
            return new JsonMapper().writeValueAsString(devices).replace("\"", "");
        }

        return null;
    }
}
