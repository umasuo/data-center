package com.umasuo.datacenter.application.dto.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umasuo.datacenter.application.dto.DeviceDataDraft;
import com.umasuo.datacenter.application.dto.DeviceDataView;
import com.umasuo.datacenter.domain.model.DeviceData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by umasuo on 17/3/9.
 */
public class DeviceDataMapper {

  private static ObjectMapper mapper = new ObjectMapper();

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
//      model.setData(view.getData());

      model.setUserId(view.getUserId());
      model.setDeveloperId(view.getDeveloperId());
      model.setDeviceDefinitionId(view.getDeviceDefinitionId());
    }
    return model;
  }


  public static DeviceData viewToModel(DeviceDataDraft dataDraft, String developerId, String
      userId) {
    DeviceData model = null;
    if (dataDraft != null) {
      model = new DeviceData();
      model.setDataDefinitionId(dataDraft.getDataId());
      model.setDeviceId(dataDraft.getDeviceId());
      model.setData(dataDraft.getData().toString());

      model.setUserId(userId);
      model.setDeveloperId(developerId);
      model.setDeviceDefinitionId(dataDraft.getDeviceDefinitionId());
    }
    return model;
  }

  public static DeviceDataView toView(DeviceData model) {
    DeviceDataView view = null;
    if (model != null) {
      view = new DeviceDataView();
      view.setId(model.getId());
      view.setCreatedAt(model.getCreatedAt());
      view.setLastModifiedAt(model.getLastModifiedAt());
      view.setDeviceId(model.getDeviceId());
      view.setDataDefinitionId(model.getDataDefinitionId());
      view.setVersion(model.getVersion());
      try {
        view.setData(mapper.readTree(model.getData()));
      } catch (IOException ex) {
        view.setData(null);
      }

      view.setUserId(model.getUserId());
      view.setDeveloperId(model.getDeveloperId());
      view.setDeviceDefinitionId(model.getDeviceDefinitionId());
    }
    return view;
  }

  public static List<DeviceDataView> toView(List<DeviceData> dataList) {
    List<DeviceDataView> viewList = new ArrayList<>();
    dataList.stream().forEach(
        data -> viewList.add(toView(data))
    );
    return viewList;
  }
}
