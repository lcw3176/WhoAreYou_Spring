package com.joebrooks.whoareyou.Interceptor;

import com.joebrooks.whoareyou.Common.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class HandShakeInterceptor extends HttpSessionHandshakeInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    @Value("${jwt.header.common}")
    private String headerName;

    @Value("${jwt.header.deviceOnly}")
    private String deviceHeader;

    @Value("${jwt.header.deviceOnly.deviceSeparator}")
    private String deviceSeparator;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        String token = request.getHeaders().get(headerName).get(0);
        String email = jwtTokenProvider.getEmailFromClaims(token);

        if(request.getHeaders().get(deviceHeader) != null){
            email += deviceSeparator;
        }

        attributes.put("email", email);

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

}
