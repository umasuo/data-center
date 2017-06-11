package com.umasuo.datacenter.application.service;

import com.umasuo.datacenter.application.dto.DataDefinition;
import com.umasuo.datacenter.application.dto.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by umasuo on 17/5/22.
 */
@Component
public class RestClient {

  /**
   * logger.
   */
  private final static Logger logger = LoggerFactory.getLogger(RestClient.class);

  /**
   * cart service uri
   */
  @Value("${developer.service.uri:http://developer/}")
  private transient String developerUrl;

  /**
   * RestTemplate.
   */
  private transient RestTemplate restTemplate = new RestTemplate();

  /**
   * 调取开发者服务，检查给定的开发者是否存在.
   *
   * @param deviceId 开发者ID
   * @return boolean
   */
  public Device getDevice(String deviceId) {
    return null;
  }


  /**
   * get data definition with dataId and developerId.
   *
   * @param dataId      String
   * @param developerId String
   * @return DataDefinition
   */
  public DataDefinition getDataDefinition(String dataId, String developerId) {

    return null;
  }
}
