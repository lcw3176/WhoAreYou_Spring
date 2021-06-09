package com.joebrooks.whoareyou.Handler;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.joebrooks.whoareyou.DTO.Device;
import com.joebrooks.whoareyou.Repository.LogRepository;
import com.joebrooks.whoareyou.Service.DeviceService;
import com.joebrooks.whoareyou.Service.LogService;
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
    private final LogService logService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        String[] values = message.getPayload().toString().split(",");
        String email = values[0];
        String deviceName = values[1];
        String state = values[2];

        if(state.equals("create")){
            System.out.println(email);
            System.out.println(deviceName + " 등록됨");
            deviceService.addDeviceByEmailAndDeviceName(email, deviceName);

        } else {
            System.out.println(email + "에게 보냄:" + deviceName + "," + state);
            logService.saveLogs(email, deviceName, state);
        }

        Device device = deviceService.getDeviceByEmailAndDeviceName(email, deviceName);
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("name", device.getName());
        obj.put("state", state);

        for(var i : sessionMap.keySet()){
            if(i.equals(email)){
                try{
                    sessionMap.get(i).sendMessage(new TextMessage(new JsonMapper().writeValueAsString(obj)));
                } catch (Exception e){
                    System.out.println("에러");
                }

            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getAttributes().get("email").toString(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getAttributes().get("email").toString());
    }

}
