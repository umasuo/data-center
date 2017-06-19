package com.umasuo.datacenter.application.rest;

import com.umasuo.datacenter.application.dto.DeviceDataDraft;
import com.umasuo.datacenter.application.dto.DeviceDataView;
import com.umasuo.datacenter.application.service.DataCreateApplication;
import com.umasuo.datacenter.infrastructure.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
  private transient DataCreateApplication dataApp;

  /**
   * 客户端或者设备上传数据.
   *
   * @param dataDraft   DeviceDataDraft
   * @param developerId String
   * @param userId      Strings
   */
//  @PostMapping(value = Router.DATA_CENTER_ROOT)
  public void createOneDeviceData(@RequestBody @Valid DeviceDataDraft dataDraft,
                                  @RequestHeader String developerId,
                                  @RequestHeader String userId) {
    logger.info("Enter. deviceDataDraft: {}, developerId: {}, userId: {}.", dataDraft,
        developerId, userId);

    DeviceDataView view = dataApp.create(dataDraft, developerId, userId);

    logger.info("Exit. dataView: {}.", view);
  }

  /**
   * 客户端或者设备上传一系列数据.
   */
  @PostMapping(value = Router.DATA_CENTER_ROOT)
  public void createListDeviceData(@RequestBody @Valid List<DeviceDataDraft>
                                       dataDrafts,
                                   @RequestHeader String developerId,
                                   @RequestHeader String userId) {
    logger.info("Enter. deviceDataDraftSize: {}, developerId: {}, userId: {}.", dataDrafts.size(),
        developerId, userId);

    List<DeviceDataView> views = dataApp.createList(dataDrafts, developerId, userId);

    logger.info("Exit. dataViewSize: {}.", views.size());
  }


  /**
   * 获取某个用户的某个设备的某种类型的数据在某一段时间的值.
   * 时间跨度不能够太大，否则会导致数据量过大而程序奔溃，用户拉取数据，限制在一次只能够拉取一周的数据.
   *
   * @param developerId 开发者ID
   * @param userId      用户ID
   * @param dataId      数据ID，开发者定义
   * @param start       开始时间
   * @param end         结束时间
   * @return 数据列表
   */
  @GetMapping(Router.DATA_CENTER_ROOT)
  public List<DeviceDataView> getDeviceData(@RequestHeader String developerId,
                                            @RequestHeader String userId,
                                            @RequestParam String dataId,
                                            @RequestParam String deviceId,
                                            @RequestParam long start,
                                            @RequestParam long end) {
    logger.info("Enter. developerId: {}, userId: {}, dataId: {}, start: {}, end: {}.",
        developerId, userId, dataId, start, end);

    List<DeviceDataView> views = dataApp.get(developerId, userId, dataId, deviceId, start, end);

    logger.info("Exit. DeviceDataSize: {}.", views.size());
    return views;
  }
}
