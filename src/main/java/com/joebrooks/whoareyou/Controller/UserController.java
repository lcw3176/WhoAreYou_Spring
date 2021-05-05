package com.joebrooks.whoareyou.Controller;

import com.joebrooks.whoareyou.Common.JwtTokenProvider;
import com.joebrooks.whoareyou.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/{userId}/{userPw}")
    public ResponseEntity trySignIn(@PathVariable("userId") String id, @PathVariable("userPw") String pw){

        if(!userService.isMember(id, pw)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .body(jwtTokenProvider.createToken(id));
    }


    @PostMapping
    public ResponseEntity trySignUp(@RequestParam("email") String email, @RequestParam("password") String pw){

        if(!userService.trySignUp(email, pw)){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
