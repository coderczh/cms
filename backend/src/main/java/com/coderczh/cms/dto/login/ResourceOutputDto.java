package com.coderczh.cms.dto.login;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ResourceOutputDto {
    private String id;
    private String name;
    private String url;
    private String icon;
    private String sort;
    private String permission;
    private List<SecondMenu> secondMenu;

    @Data
    public static class SecondMenu {
        private String id;
        private String name;
        private String url;
        private String icon;
        private String sort;
        private String permission;
        private List<ThirdMenu> thirdMenu;
    }

    @Data
    public static class ThirdMenu {
        private String id;
        private String name;
        private String url;
        private String icon;
        private String sort;
        private String permission;
    }
}
