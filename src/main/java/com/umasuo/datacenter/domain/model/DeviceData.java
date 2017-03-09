package com.umasuo.datacenter.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by umasuo on 17/3/9.
 * Device Data
 */
@Entity
@Table(name = "device_data")
@Setter
@Getter
public class DeviceData {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * The Created at.
   */
  @CreatedDate
  @Column(name = "created_at")
  protected ZonedDateTime createdAt;

  /**
   * The Last modified at.
   * Maybe we do not need this.
   */
  @LastModifiedDate
  @Column(name = "last_modified_at")
  protected ZonedDateTime lastModifiedAt;

  /**
   * version used for update date check.
   */
  private Integer version;

  /**
   * data definition id, used to check if the data is in the correct structure.
   */
  private String dataDefinitionId;

  /**
   * device id.
   */
  private String deviceId;

  /**
   * the real structured json data.
   */
  private String data;

  // the next three filed is redundancy for search or process.

  private String userId;

  private String developerId;

  private String deviceDefinitionId;


}
