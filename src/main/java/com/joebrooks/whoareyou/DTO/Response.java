package com.joebrooks.whoareyou.DTO;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.HashMap;

@Data
@Builder
public class Response {
    private int code;
    private String result;

    @SneakyThrows
    @Override
    public String toString(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("result", result);

        return new JsonMapper().writeValueAsString(map);
    }

}

