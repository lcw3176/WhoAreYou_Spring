package com.joebrooks.whoareyou.Interceptor;

import com.joebrooks.whoareyou.Common.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
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

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        String token = request.getHeaders().get("X-AUTH-TOKEN").get(0);
        String email = jwtTokenProvider.getEmailFromClaims(token);
        attributes.put("email", email);

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

}
