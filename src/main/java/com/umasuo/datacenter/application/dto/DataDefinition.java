package com.umasuo.datacenter.application.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.umasuo.datacenter.infrastructure.util.JsonUtils;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Created by umasuo on 17/3/8.
 */
@Data
public class DataDefinition implements Serializable {

  /**
   * auto generated serial id.
   */
  private static final long serialVersionUID = 944833177372407120L;

  /**
   * auto generated uuid.
   */
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
   * which developer this data definition belong to.
   */
  private String developerId;

  /**
   * data id defined by the developer.
   */
  private String dataId;

  /**
   * the data structure.
   */
  private JsonNode dataSchema;

  private String schema;

  /**
   * name of this definition.
   */
  private String name;

  /**
   * describe the usage of this definition.
   */
  private String description;

  /**
   * The Openable.
   * True means other developers can find this data, false means not.
   */
  private Boolean openable;

  public void setSchema(String schema) {
    this.schema = schema;
    dataSchema = JsonUtils.deserialize(schema, JsonNode.class);
  }
}
