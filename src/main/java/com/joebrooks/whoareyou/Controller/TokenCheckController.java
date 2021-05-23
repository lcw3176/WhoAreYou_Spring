package com.joebrooks.whoareyou.Controller;

import com.joebrooks.whoareyou.Common.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity checkValidation(@PathVariable("token") String token){

        if(!jwtTokenProvider.isValidate(token)){
            
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(token);
    }
}
