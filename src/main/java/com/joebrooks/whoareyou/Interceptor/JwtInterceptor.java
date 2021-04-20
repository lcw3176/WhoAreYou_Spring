package com.joebrooks.whoareyou.Interceptor;

import com.joebrooks.whoareyou.Common.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{
        String token = jwtTokenProvider.resolveToken(request);

        if(token == null || !jwtTokenProvider.isValidate(token)){
            response.sendRedirect("/error/noAuth");
            return false;
        }

        return true;
    }

}
