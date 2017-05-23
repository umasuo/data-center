package com.umasuo.datacenter.application.service;

import com.umasuo.datacenter.application.dto.DeviceDataDraft;
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
 * 设备上传数据服务.
 * Created by umasuo on 17/3/9.
 */
@Service
public class DataCreateService {

  /**
   * logger.
   */
  private final static Logger logger = LoggerFactory.getLogger(DeviceDataService.class);

  /**
   * 设备数据服务
   */
  @Autowired
  private transient DeviceDataService deviceDataService;

  /**
   * 所有设备数据存储的唯一接口，此接口的压力会非常大，尽量优化.
   *
   * @param dataDraft
   * @return
   */
  public DeviceDataView create(DeviceDataDraft dataDraft) {
    logger.debug("CreateDeviceData: dataDraft: {}", dataDraft);

    DeviceData data = DeviceDataMapper.viewToModel(dataDraft);

    //TODO 1, check if the device exist
    //TODO 2, check if the user bind to the device,(if the device is an open de)
    //TODO 3, check if the data is in correct structure
    //TODO 4, if the data is not the final data, then it need to be processed before saved it
    // into db, then we should call data processor here.

    DeviceData dataSaved = deviceDataService.create(data);

    logger.debug("CreateDeviceData: dataSaved: {}", dataSaved);
    return DeviceDataMapper.modelToView(dataSaved);
  }
}
