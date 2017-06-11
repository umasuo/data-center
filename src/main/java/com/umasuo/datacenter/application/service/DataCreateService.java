package com.umasuo.datacenter.application.service;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.umasuo.datacenter.application.dto.DataDefinition;
import com.umasuo.datacenter.application.dto.Device;
import com.umasuo.datacenter.application.dto.DeviceDataDraft;
import com.umasuo.datacenter.application.dto.DeviceDataView;
import com.umasuo.datacenter.application.dto.mapper.DeviceDataMapper;
import com.umasuo.datacenter.domain.model.DeviceData;
import com.umasuo.datacenter.domain.service.DeviceDataService;
import com.umasuo.exception.ParametersException;
import io.jsonwebtoken.lang.Assert;
import lombok.Data;
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

  @Autowired
  private transient RestClient restClient;

  /**
   * 所有设备数据存储的唯一接口，此接口的压力会非常大，尽量优化.
   * 所有数据均由设备产生.
   *
   * @param dataDraft
   * @return
   */
  public DeviceDataView create(DeviceDataDraft dataDraft, String developerId, String userId) {
    logger.debug("Enter. dataDraft: {}", dataDraft);

    DeviceData data = DeviceDataMapper.viewToModel(dataDraft, developerId, userId);

    //TODO 1, check if the device exist
    Device device = restClient.getDevice(dataDraft.getDeviceId());
    //TODO 2, check if the user bind to the device,(if the device is an open device)

    //TODO 3, check if the data is in correct structure
    DataDefinition dataDefinition = restClient.getDataDefinition(dataDraft.getDataId(),
        developerId);
    try {
      JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(dataDefinition
          .getDataSchema());
      schema.validate(dataDraft.getData());
    } catch (ProcessingException ex) {
      logger.debug("Data is not in correct format, dataDraft: {}.", dataDraft);
      throw new ParametersException("Data is not in correct format.");
    }

    //TODO 4, if the data is not the final data, then it need to be processed before saved it
    // into db, then we should call data processor here. 此步骤需要异步执行，否则容易出错

    DeviceData dataSaved = deviceDataService.create(data);

    logger.debug("Exit. dataSaved: {}", dataSaved);
    return DeviceDataMapper.modelToView(dataSaved);
  }
}
