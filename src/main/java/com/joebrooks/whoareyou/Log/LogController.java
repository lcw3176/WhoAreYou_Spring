package com.joebrooks.whoareyou.Log;

import com.joebrooks.whoareyou.User.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/log")
public class LogController {
    private final LogRepository logRepository;
    private final UserRepository userRepository;

    public LogController(LogRepository logRepository, UserRepository userRepository){
        this.logRepository = logRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public Log GetInfo(){
//        User user = userRepository.findByNumber("010-1111-1111");
//
//        logRepository.save(Log.builder()
//                              .state(1)
//                              .user(user)
//                              .build());
        return logRepository.findByUser(userRepository.findByNumber("010-1111-1111"));
    }
}
