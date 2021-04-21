package com.joebrooks.whoareyou.Controller;

import com.joebrooks.whoareyou.Common.JwtTokenProvider;
import com.joebrooks.whoareyou.Common.Response.ResponseCode;
import com.joebrooks.whoareyou.Common.Response.ResponseResult;
import com.joebrooks.whoareyou.DTO.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation")
@RequiredArgsConstructor
public class TokenCheckController {
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/{token}")
    public Response checkValidation(@PathVariable("token") String token){

        if(!jwtTokenProvider.isValidate(token)){
            
            return Response.builder()
                    .code(ResponseCode.wrongRequest)
                    .result(ResponseResult.wrongValue)
                    .build();
        }

        return Response.builder()
                .code(ResponseCode.success)
                .build();
    }
}
