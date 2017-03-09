package com.umasuo.datacenter.application.dto;

import lombok.Data;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

/**
 * Created by umasuo on 17/3/9.
 */
@Data
public class DeviceDataView {

  private String id;

  protected ZonedDateTime createdAt;

  protected ZonedDateTime lastModifiedAt;

  private Integer version;

  /**
   * data definition id, used to check if the data is in the correct structure.
   */
  @NotNull
  private String dataDefinitionId;

  /**
   * device id.
   */
  @NotNull
  private String deviceId;

  /**
   * the real structured json data.
   */
  @NotNull
  private String data;

  // the next three filed is redundancy for search or process.
  @NotNull
  private String userId;

  private String developerId;

  private String deviceDefinitionId;
}
