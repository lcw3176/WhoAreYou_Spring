package com.joebrooks.whoareyou.Repository;

import com.joebrooks.whoareyou.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmailAndPassword(String email, String pw);
    UserEntity findByEmail(String email);
    Boolean existsByEmail(String email);
}
