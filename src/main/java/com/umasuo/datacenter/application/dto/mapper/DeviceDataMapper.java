package com.umasuo.datacenter.application.dto.mapper;

import com.umasuo.datacenter.application.dto.DeviceDataView;
import com.umasuo.datacenter.domain.model.DeviceData;

/**
 * Created by umasuo on 17/3/9.
 */
public class DeviceDataMapper {

  public static DeviceData viewToModel(DeviceDataView view) {
    DeviceData model = null;
    if (view != null) {
      model = new DeviceData();
      model.setId(view.getId());
      model.setCreatedAt(view.getCreatedAt());
      model.setLastModifiedAt(view.getLastModifiedAt());
      model.setDeviceId(view.getDeviceId());
      model.setDataDefinitionId(view.getDataDefinitionId());
      model.setVersion(view.getVersion());
      model.setData(view.getData());

      model.setUserId(view.getUserId());
      model.setDeveloperId(view.getDeveloperId());
      model.setDeviceDefinitionId(view.getDeviceDefinitionId());
    }
    return model;
  }

  public static DeviceDataView modelToView(DeviceData model) {
    DeviceDataView view = null;
    if (model != null) {
      view = new DeviceDataView();
      view.setId(model.getId());
      view.setCreatedAt(model.getCreatedAt());
      view.setLastModifiedAt(model.getLastModifiedAt());
      view.setDeviceId(model.getDeviceId());
      view.setDataDefinitionId(model.getDataDefinitionId());
      view.setVersion(model.getVersion());
      view.setData(model.getData());

      view.setUserId(model.getUserId());
      view.setDeveloperId(model.getDeveloperId());
      view.setDeviceDefinitionId(model.getDeviceDefinitionId());
    }
    return view;
  }
}
