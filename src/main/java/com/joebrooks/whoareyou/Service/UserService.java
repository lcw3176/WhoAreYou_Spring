package com.joebrooks.whoareyou.Service;

import com.joebrooks.whoareyou.Entity.UserEntity;
import com.joebrooks.whoareyou.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Boolean isMember(String email, String pw){
        Object result = userRepository.findByEmailAndPassword(email, pw);

        if(result == null){
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
