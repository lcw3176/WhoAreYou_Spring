package com.joebrooks.whoareyou.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.joebrooks.whoareyou.DTO.Log;
import com.joebrooks.whoareyou.Entity.LogEntity;
import com.joebrooks.whoareyou.Entity.UserEntity;
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
    private final UserRepository userRepository;

    public String getLogs(String email) throws JsonProcessingException {
        UserEntity user = userRepository.findByEmail(email);
        List<Log> logs = new ArrayList<>();

        for(var i : logRepository.findAllByUserIdx(user.getIdx())){
            logs.add(Log.builder()
                    .time(i.getTime())
                    .state(i.getState() != 0)
                    .build());
        }

        JsonMapper mapper = new JsonMapper();

        if (logs.size() != 0) {
            return mapper.writeValueAsString(logs).replace("\"", "");
        }

        return null;
    }
}
