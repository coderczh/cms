package com.coderczh.cms.service;

import com.coderczh.cms.entity.RoleResource;

import java.util.List;

public interface RoleResourceService {
    List<RoleResource> getResourceIdByRoleId(Integer roleId);
}
