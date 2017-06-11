package com.umasuo.datacenter.application.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by umasuo on 17/6/9.
 */
@Data
public class Device implements Serializable {

  /**
   * auto generated serial id.
   */
  private static final long serialVersionUID = -9049122749850283417L;

  private String id;

  /**
   * The Created at.
   */
  protected ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  protected ZonedDateTime lastModifiedAt;

  /**
   * version used for update date check.
   */
  private Integer version;

  /**
   * device definition id.
   */
  private String deviceDefineId;

  /**
   * 开发者自定义的设备ID.
   */
  private String customizedId;

  private String ownerId;

  private String developerId;
}
