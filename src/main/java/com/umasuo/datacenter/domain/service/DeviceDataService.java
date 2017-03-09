package com.umasuo.datacenter.domain.service;

import com.umasuo.datacenter.domain.model.DeviceData;
import com.umasuo.datacenter.infrastructure.repository.DeviceDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    logger.debug("CreateDeviceData: {}", data);
    Assert.notNull(data);
    Assert.notNull(data.getData());
    Assert.notNull(data.getDataDefinitionId());
    Assert.notNull(data.getDeviceId());
    Assert.notNull(data.getUserId());

    return repository.save(data);
  }

}
