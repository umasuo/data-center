package com.umasuo.datacenter.infrastructure.repository;

import com.umasuo.datacenter.domain.model.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by umasuo on 17/2/10.
 */
@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceData, String> {

}
