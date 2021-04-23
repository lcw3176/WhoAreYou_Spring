package com.joebrooks.whoareyou.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.joebrooks.whoareyou.DTO.Log;
import com.joebrooks.whoareyou.Entity.DeviceEntity;
import com.joebrooks.whoareyou.Entity.UserEntity;
import com.joebrooks.whoareyou.Repository.DeviceRepository;
import com.joebrooks.whoareyou.Repository.LogRepository;
import com.joebrooks.whoareyou.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    public String getLogs(String deviceName, String userId) throws JsonProcessingException {
        UserEntity user = userRepository.findByEmail(userId);
        DeviceEntity device = deviceRepository.findByNameAndUser_Idx(deviceName, user.getIdx());

        if(device != null){
            List<Log> logs = new ArrayList<>();

            for(var i : logRepository.findAllByDevice_IdxOrderByTimeDesc(device.getIdx())){
                logs.add(Log.builder()
                        .time(i.getTime())
                        .state(i.getState() != 0)
                        .build());
            }

            return new JsonMapper().writeValueAsString(logs);
        }

        return null;
    }
}
