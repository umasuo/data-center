package com.umasuo.datacenter.infrastructure.repository;

import com.umasuo.datacenter.domain.model.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by umasuo on 17/2/10.
 */
@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceData, String> {

  @Query("select u from User u where u.developerId = ?1 and  u.userId = ?2 and u.dataDefinitionId = ?3 and u.deviceId = ?4 and u.created_at > ?5 and u.created_at < ?6")
  List<DeviceData> queryByTime(String developerId,
                               String userId,
                               String dataId,
                               String deviceId,
                               long start,
                               long end);

}
