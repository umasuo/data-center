package com.umasuo.datacenter.domain.service;

import com.umasuo.datacenter.domain.model.DeviceData;
import com.umasuo.datacenter.infrastructure.repository.DeviceDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by umasuo on 17/3/9.
 */
@Service
public class DeviceDataService {

  /**
   * logger.
   */
  private final static Logger logger = LoggerFactory.getLogger(DeviceDataService.class);

  @Autowired
  private DeviceDataRepository repository;

  /**
   * create one device data.
   *
   * @param data
   * @return
   */
  public DeviceData create(DeviceData data) {
    logger.debug("Enter. {}", data);

    DeviceData saved = repository.save(data);

    logger.debug("Exit. saved: {}.", saved);
    return saved;
  }

  public List<DeviceData> get(String developerId,
                              String userId,
                              String dataId,
                              String deviceId,
                              long start,
                              long end) {
    logger.debug("Enter. developerId: {}, userId: {}, dataId: {}, deviceId: {}, start: {}, end: " +
        "{}.", developerId, userId, dataId, deviceId, start, end);
    List<DeviceData> dataList = repository.queryByTime(developerId, userId, dataId, deviceId,
        start, end);

    logger.debug("Exit. dataSize: {}.", dataList.size());
    logger.trace("Exit. data: {}.", dataList);
    return dataList;
  }

}
