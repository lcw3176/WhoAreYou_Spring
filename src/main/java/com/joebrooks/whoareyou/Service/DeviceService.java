package com.joebrooks.whoareyou.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.joebrooks.whoareyou.Common.JwtTokenProvider;
import com.joebrooks.whoareyou.DTO.Device;
import com.joebrooks.whoareyou.Entity.DeviceEntity;
import com.joebrooks.whoareyou.Entity.UserEntity;
import com.joebrooks.whoareyou.Repository.DeviceRepository;
import com.joebrooks.whoareyou.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public String getAllDevices(String email) throws JsonProcessingException {
        UserEntity user = userRepository.findByEmail(email);
        List<DeviceEntity> entities = deviceRepository.findAllByUser_Idx(user.getIdx());

        if(entities.size() != 0){
            List<Device> devices = new ArrayList<>();

            for(var i : entities){
                devices.add(Device.builder()
                        .name(i.getName())
                        .build());

            }

            return new JsonMapper().writeValueAsString(devices);
        }


        return null;
    }

    public Device getDeviceByTokenAndDeviceName(String token, String deviceName){
        String email = jwtTokenProvider.getEmailFromClaims(token);

        UserEntity user = userRepository.findByEmail(email);
        DeviceEntity entity = deviceRepository.findByNameAndUser_Idx(deviceName, user.getIdx());

        if(entity != null){

            return Device.builder()
                    .name(entity.getName())
                    .build();
        }


        return null;
    }
}
