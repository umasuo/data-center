package com.umasuo.datacenter.application.rest;

import com.umasuo.datacenter.application.dto.DeviceDataDraft;
import com.umasuo.datacenter.application.dto.DeviceDataView;
import com.umasuo.datacenter.application.service.DataCreateService;
import com.umasuo.datacenter.infrastructure.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by umasuo on 17/3/9.
 */
@RestController
@CrossOrigin
public class DeviceDataController {

  /**
   * logger.
   */
  private final static Logger logger = LoggerFactory.getLogger(DeviceDataController.class);

  @Autowired
  private transient DataCreateService dataCreateService;

  /**
   * 创建一条数据，只能够创建一条.
   *
   * @param dataDraft
   */
  @PostMapping(value = Router.DATA_CENTER_ROOT)
  public void createOneDeviceData(@RequestBody @Valid DeviceDataDraft dataDraft) {
    logger.info("Enter. deviceDataDraft: {}.", dataDraft);

    dataCreateService.create(dataDraft);
  }

}
