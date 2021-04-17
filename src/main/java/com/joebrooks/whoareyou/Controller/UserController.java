package com.joebrooks.whoareyou.Controller;

import com.joebrooks.whoareyou.Common.Response.ResponseCode;
import com.joebrooks.whoareyou.Common.Response.ResponseResult;
import com.joebrooks.whoareyou.DTO.Response;
import com.joebrooks.whoareyou.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
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
