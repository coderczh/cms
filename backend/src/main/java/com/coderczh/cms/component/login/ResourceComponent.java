package com.coderczh.cms.component.login;

import cn.hutool.core.convert.Convert;
import com.coderczh.cms.dto.login.ResourceOutputDto;
import com.coderczh.cms.entity.ResourceFirst;
import com.coderczh.cms.entity.ResourceSecond;
import com.coderczh.cms.entity.ResourceThird;
import com.coderczh.cms.entity.RoleResource;
import com.coderczh.cms.service.ResourceFirstService;
import com.coderczh.cms.service.ResourceSecondService;
import com.coderczh.cms.service.ResourceThirdService;
import com.coderczh.cms.service.RoleResourceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ResourceComponent {

    @Resource
    private RoleResourceService roleResourceService;

    @Resource
    private ResourceFirstService resourceFirstService;

    @Resource
    private ResourceSecondService resourceSecondService;

    @Resource
    private ResourceThirdService resourceThirdService;

    public ResourceOutputDto getResourceByRoleId(Integer roleId) {
        Map<ResourceFirst, Integer> resourceFirstMap = new HashMap<>();
        Map<ResourceSecond, Integer> resourceSecondMap = new HashMap<>();
        Map<ResourceThird, Integer> resourceThirdMap = new HashMap<>();
        List<RoleResource> resourceList = roleResourceService.getResourceIdByRoleId(roleId);
        resourceList.forEach(resource -> {
            ResourceFirst first = resourceFirstService.getResourceFirstById(resource.getFirstId());
            resourceFirstMap.put(first, first.getId());
            if (resource.getSecondId() == null && resource.getThirdId() == null) {
                List<ResourceSecond> secondList = resourceSecondService.getResourceSecondByFirstId(first.getId());
                resourceSecondMap.putAll(secondList.stream().collect(
                        Collectors.toMap(second -> second, ResourceSecond::getFirstId)));
                secondList.forEach(second -> {
                    List<ResourceThird> thirdList = resourceThirdService.getResourceThirdBySecondId(second.getId());
                    resourceThirdMap.putAll(thirdList.stream().collect(
                            Collectors.toMap(third -> third, ResourceThird::getSecondId)));
                });
            }
        });
        return getResourceOutputDto(resourceFirstMap, resourceSecondMap, resourceThirdMap);
    }

    private ResourceOutputDto getResourceOutputDto(Map<ResourceFirst, Integer> resourceFirstMap,
                                      Map<ResourceSecond, Integer> resourceSecondMap,
                                      Map<ResourceThird, Integer> resourceThirdMap) {
        /*resourceFirstMap.keySet().forEach(resourceFirst ->
                resourceSecondMap.keySet().forEach(resourceSecond ->
                        resourceThirdMap.keySet().forEach(resourceThird -> {
                            resourceSecond

                            ResourceOutputDto.ThirdMenu thirdMenu
                                    = Convert.convert(ResourceOutputDto.ThirdMenu.class, resourceThird);

                        })));*/
        return null;
    }
}
