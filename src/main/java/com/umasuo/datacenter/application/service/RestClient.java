package com.umasuo.datacenter.application.service;

import com.umasuo.datacenter.application.dto.DataDefinition;
import com.umasuo.datacenter.application.dto.Device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.InvalidMediaTypeException;
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
   * Data center url.
   */
  @Value("${device.center.service.url:http://device-center/}")
  private String deviceCenterUrl;

  /**
   * Data definition service url.
   */
  @Value("${data.definition.service.url:http://data-definition/}")
  private String dataDefinitionUrl;

  /**
   * RestTemplate.
   */
  private transient RestTemplate restTemplate = new RestTemplate();

  /**
   * 调取开发者服务，检查给定的开发者是否存在.
   *
   * @param deviceId 设备ID
   * @param developerId 开发者Id
   * @return Device
   */
  public Device getDevice(String deviceId, String developerId) {
    logger.debug("Enter. deviceId: {}, developerId: {}.", deviceId, developerId);

    HttpEntity entity = addDeveloperHeader(developerId);

    String url = deviceCenterUrl + deviceId;

    Device result = null;

    try {

      HttpEntity<Device> response =
          restTemplate.exchange(url, HttpMethod.GET, entity, Device.class);

      result = response.getBody();
    } catch (InvalidMediaTypeException exception) {
      logger.debug("Can not find Device: ", exception);
    }

    logger.debug("Exit. device: {}.", result);
    return result;
  }

  /**
   * get data definition with dataId and developerId.
   *
   * @param dataDefinitionId      String
   * @param developerId String
   * @return DataDefinition
   */
  public DataDefinition getDataDefinition(String dataDefinitionId, String developerId) {

    logger.debug("Enter. dataDefinitionId: {}, developerId: {}.", dataDefinitionId, developerId);

    String url = dataDefinitionUrl + dataDefinitionId;

    HttpEntity entity = addDeveloperHeader(developerId);

    DataDefinition result = null;

    try {
      HttpEntity<DataDefinition> response =
          restTemplate.exchange(url, HttpMethod.GET, entity, DataDefinition.class);

      result = response.getBody();
    } catch (InvalidMediaTypeException exception) {
      logger.debug("Can not find Device: ", exception);
    }

    logger.debug("Exit. dataDefinition: {}.", result);

    return result;
  }

  /**
   * Add developer id into header.
   *
   * @param developerId the developer id
   * @return HttpEntity
   */
  private HttpEntity addDeveloperHeader(String developerId) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("developerId", developerId);

    return new HttpEntity(headers);
  }
}
