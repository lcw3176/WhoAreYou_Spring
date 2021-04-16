package com.joebrooks.whoareyou.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNumber(String number);
}
