package com.umasuo.datacenter.infrastructure.repository;

import com.umasuo.datacenter.domain.model.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by umasuo on 17/2/10.
 */
@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceData, String> {

  @Query("select d from DeviceData d where d.developerId = ?1 and  d.userId = ?2 and d.dataDefinitionId = ?3 and d.deviceId = ?4 and d.createdAt > ?5 and d.lastModifiedAt < ?6")
  List<DeviceData> queryByTime(String developerId,
                               String userId,
                               String dataId,
                               String deviceId,
                               long start,
                               long end);

}
