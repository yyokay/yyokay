package org.yyok.oauth.repository;

import java.util.List;
import java.util.Map;
import org.yyok.oauth.bean.*;
import org.yyok.oauth.bean.Permission;

public interface PermissionMapper {

    List<Permission> getByMap(Map<String, Object> map);

    Permission getById(Integer id);

    Integer create(Permission permission);

    int update(Permission permission);

    List<Permission> getByUserId(Integer userId);

}
