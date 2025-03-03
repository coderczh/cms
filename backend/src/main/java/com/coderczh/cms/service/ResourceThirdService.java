package com.coderczh.cms.service;

import com.coderczh.cms.entity.ResourceThird;

import java.util.List;

public interface ResourceThirdService {
    ResourceThird getResourceThirdById(Integer id);

    List<ResourceThird> getResourceThirdBySecondId(Integer secondId);
}
