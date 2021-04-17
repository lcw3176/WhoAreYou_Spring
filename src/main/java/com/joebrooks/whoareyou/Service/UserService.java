package com.joebrooks.whoareyou.Service;

import com.joebrooks.whoareyou.Entity.UserEntity;
import com.joebrooks.whoareyou.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Boolean isMember(String email, String pw){
        Object obj = userRepository.findByEmailAndPassword(email, pw);

        if(obj == null){
            return false;
        }

        return true;
    }

    public Boolean trySignUp(String email, String pw){
        if(userRepository.existsByEmail(email)){
            return false;
        }

        userRepository.save(UserEntity.builder().email(email).password(pw).build());

        return true;
    }

}