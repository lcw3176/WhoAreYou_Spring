package com.joebrooks.whoareyou.Controller;

import com.joebrooks.whoareyou.Common.Response.ResponseCode;
import com.joebrooks.whoareyou.Common.Response.ResponseResult;
import com.joebrooks.whoareyou.DTO.Response;
import com.joebrooks.whoareyou.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signin")
public class SignInController {
    private final UserService userService;

    public SignInController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{userId}/{userPw}")
    public Response tryLogin(@PathVariable("userId") String id, @PathVariable("userPw") String pw){

        if(!userService.isMember(id, pw)){

            return Response.builder()
                           .code(ResponseCode.notFound)
                           .result(ResponseResult.notMember.toString())
                           .build();
        }

        return Response.builder()
                       .code(ResponseCode.success)
                       .build();
    }
}
