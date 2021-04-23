package com.joebrooks.whoareyou.Repository;

import com.joebrooks.whoareyou.Entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> findAllByUser_Idx(Long userIdx);
    DeviceEntity findByNameAndUser_Idx(String name, Long userIdx);
}
