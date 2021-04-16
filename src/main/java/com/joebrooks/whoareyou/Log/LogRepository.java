package com.joebrooks.whoareyou.Log;

import com.joebrooks.whoareyou.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
    Log findByUser(User user);
}
