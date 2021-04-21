package com.joebrooks.whoareyou.Controller;

import com.joebrooks.whoareyou.Common.Response.ResponseCode;
import com.joebrooks.whoareyou.Common.Response.ResponseResult;
import com.joebrooks.whoareyou.DTO.Response;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public Response showError(){

        return Response.builder()
                .code(ResponseCode.wrongRequest)
                .result(ResponseResult.wrongRequest)
                .build();
    }

    @GetMapping("/error/noAuth")
    public Response showNoAuth(){

        return Response.builder()
                .code(ResponseCode.notAllowed)
                .result(ResponseResult.notAllowed)
                .build();
    }

    // Deprecated
    @Override
    public String getErrorPath() {
        return null;
    }
}
