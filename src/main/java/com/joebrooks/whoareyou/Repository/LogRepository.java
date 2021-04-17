package com.joebrooks.whoareyou.Repository;

import com.joebrooks.whoareyou.Entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long> {

}
