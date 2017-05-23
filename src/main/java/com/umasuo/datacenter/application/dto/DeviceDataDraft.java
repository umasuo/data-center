package com.umasuo.datacenter.application.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

/**
 * Created by umasuo on 17/3/9.
 */
@Data
public class DeviceDataDraft {

  /**
   * data definition id, used to check if the data is in the correct structure.
   */
  @NotNull
  private String dataId;

  /**
   * device id.
   */
  @NotNull
  private String deviceId;

  /**
   * the real structured json data.
   */
  @NotNull
  private JsonNode data;

  /**
   * 以下个信息并不是必要的，只是为了以后的搜索优化而存储.
   */
  private String userId;

  private String developerId;

  private String deviceDefinitionId;
}
