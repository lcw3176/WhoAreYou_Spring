package com.joebrooks.whoareyou.Handler;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.joebrooks.whoareyou.Common.Response.ResponseCode;
import com.joebrooks.whoareyou.DTO.Device;
import com.joebrooks.whoareyou.DTO.Response;
import com.joebrooks.whoareyou.Service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class SocketHandler extends TextWebSocketHandler {
    private HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
    private final DeviceService deviceService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        String[] values = message.getPayload().toString().split(",");
        String token = values[0];
        String deviceName = values[1];
        String state = values[2];

        Device device = deviceService.getDeviceByTokenAndDeviceName(token, deviceName);

        HashMap<String, Object> obj = new HashMap<>();
        obj.put("name", device.getName());
        obj.put("state", state);

        for(var i : sessionMap.keySet()){
            if(i.equals(token)){
                try{
                    sessionMap.get(i).sendMessage(new TextMessage(Response.builder()
                            .code(ResponseCode.success)
                            .result(new JsonMapper().writeValueAsString(obj))
                            .build()
                            .toString()));   
                } catch (Exception e){
                    System.out.println("에러");
                }

            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getAttributes().get("token").toString(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getAttributes().get("token").toString());
    }

}
