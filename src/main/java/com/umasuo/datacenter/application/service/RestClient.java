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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Rest client.
 */
@Component
public class RestClient {

  /**
   * LOGGER.
   */
  private final static Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

  /**
   * Data center url.
   */
  @Value("${device.center.service.url:http://device-center/}")
  private transient String deviceCenterUrl;

  /**
   * Data definition service url.
   */
  @Value("${data.definition.service.url:http://data-definition/}")
  private transient String dataDefinitionUrl;

  /**
   * RestTemplate.
   */
  private transient RestTemplate restTemplate = new RestTemplate();

  /**
   * 调取开发者服务，检查给定的开发者是否存在.
   *
   * @param deviceId    设备ID
   * @param developerId 开发者Id
   * @return Device
   */
  public Device getDevice(String deviceId, String developerId) {
    LOGGER.debug("Enter. deviceId: {}, developerId: {}.", deviceId, developerId);

    HttpEntity entity = addDeveloperHeader(developerId);

    String url = deviceCenterUrl + "v1/devices/" + deviceId;

    LOGGER.debug("device url: {}.", url);

    Device result = null;

    try {

      HttpEntity<Device> response =
          restTemplate.exchange(url, HttpMethod.GET, entity, Device.class);

      result = response.getBody();
    } catch (RestClientException | InvalidMediaTypeException exception) {
      LOGGER.debug("Can not find Device: ", exception);
    }

    LOGGER.debug("Exit. device: {}.", result);
    return result;
  }

  /**
   * get data definition with dataId and developerId.
   *
   * @param dataDefinitionId String
   * @param developerId      String
   * @return DataDefinition
   */
  public DataDefinition getDataDefinition(String dataDefinitionId, String developerId,
                                          String productId) {

    LOGGER.debug("Enter. dataDefinitionId: {}, developerId: {}.", dataDefinitionId, developerId);

    String url = dataDefinitionUrl + "data-definitions/" + dataDefinitionId;

    url = UriComponentsBuilder.fromHttpUrl(url).queryParam("productId", productId).build()
        .toString();

    LOGGER.debug("DataDefinition url: {}.", url);

    HttpEntity entity = addDeveloperHeader(developerId);

    DataDefinition result = null;

    try {
      HttpEntity<DataDefinition> response =
          restTemplate.exchange(url, HttpMethod.GET, entity, DataDefinition.class);

      result = response.getBody();
    } catch (RestClientException | InvalidMediaTypeException exception) {
      LOGGER.debug("Can not find Device: ", exception);
    }

    LOGGER.debug("Exit. dataDefinition: {}.", result);

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
