package com.joebrooks.whoareyou.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public User GetInfo(){
//        userRepository.deleteAll();
//
//        for(int i = 0;i < 1000; i++){
//            userRepository.save(User.builder()
//                          .number("010-1234-1234")
//                          .build());
//        }
//
//        userRepository.save(User.builder()
//                                .number("010-1111-1111")
//                                .build());

       return Optional.of(userRepository.findByNumber("010-1111-1111"))
                      .orElseGet(User::new);
    }
}
