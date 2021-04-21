package com.joebrooks.whoareyou.Controller;

import com.joebrooks.whoareyou.Common.JwtTokenProvider;
import com.joebrooks.whoareyou.Common.Response.ResponseCode;
import com.joebrooks.whoareyou.Common.Response.ResponseResult;
import com.joebrooks.whoareyou.DTO.Response;
import com.joebrooks.whoareyou.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/{userId}/{userPw}")
    public Response tryLogin(@PathVariable("userId") String id, @PathVariable("userPw") String pw){

        if(!userService.isMember(id, pw)){

            return Response.builder()
                           .code(ResponseCode.notFound)
                           .result(ResponseResult.notMember)
                           .build();
        }

        return Response.builder()
                       .code(ResponseCode.success)
                       .result(jwtTokenProvider.createToken(id))
                       .build();
    }


    @PostMapping
    public Response trySignUp(@RequestParam("email") String email, @RequestParam("password") String pw){

        if(!userService.trySignUp(email, pw)){

            return Response.builder()
                           .code(ResponseCode.notAllowed)
                           .result(ResponseResult.existMember)
                           .build();
        }

        return Response.builder()
                       .code(ResponseCode.success)
                       .build();
    }
}
