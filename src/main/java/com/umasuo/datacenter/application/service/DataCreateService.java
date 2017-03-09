package com.umasuo.datacenter.application.service;

import com.umasuo.datacenter.application.dto.DeviceDataView;
import com.umasuo.datacenter.application.dto.mapper.DeviceDataMapper;
import com.umasuo.datacenter.domain.model.DeviceData;
import com.umasuo.datacenter.domain.service.DeviceDataService;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by umasuo on 17/3/9.
 */
@Service
public class DataCreateService {

  /**
   * logger.
   */
  private final static Logger logger = LoggerFactory.getLogger(DeviceDataService.class);

  @Autowired
  private transient DeviceDataService deviceDataService;

  /**
   * create one device data.
   *
   * @param dataView
   * @return
   */
  public DeviceDataView create(DeviceDataView dataView) {
    logger.debug("CreateDeviceData: dataView: {}", dataView);
    Assert.notNull(dataView);
    DeviceData data = DeviceDataMapper.viewToModel(dataView);

    //TODO, 1, check if the device exist
    //TODO, 2, check if the user bind to the device
    //TODO, 3, check if the data is in correct structure

    DeviceData dataSaved = deviceDataService.create(data);

    logger.debug("CreateDeviceData: dataSaved: {}", dataSaved);
    return DeviceDataMapper.modelToView(dataSaved);
  }
}
