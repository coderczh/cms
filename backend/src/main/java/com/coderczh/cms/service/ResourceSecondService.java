package com.coderczh.cms.service;

import com.coderczh.cms.entity.ResourceSecond;

import java.util.List;

public interface ResourceSecondService {
    ResourceSecond getResourceSecondById(Integer id);

    List<ResourceSecond> getResourceSecondByFirstId(Integer firstId);
}
