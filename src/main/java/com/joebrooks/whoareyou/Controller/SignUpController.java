package com.joebrooks.whoareyou.Controller;

import com.joebrooks.whoareyou.Common.Response.ResponseCode;
import com.joebrooks.whoareyou.Common.Response.ResponseResult;
import com.joebrooks.whoareyou.DTO.Response;
import com.joebrooks.whoareyou.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    private final UserService userService;

    public SignUpController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public Response trySignUp(@RequestParam("email") String email, @RequestParam("password") String pw){
        if(!userService.trySignUp(email, pw)){

            return Response.builder()
                           .code(ResponseCode.notAllowed)
                           .result(ResponseResult.existMember.toString())
                           .build();
        }

        return Response.builder()
                       .code(ResponseCode.success)
                       .build();
    }
}
