package com.joebrooks.whoareyou.Repository;

import com.joebrooks.whoareyou.Entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> findAllByUserIdx(Long userIdx);
}
