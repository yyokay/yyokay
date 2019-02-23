package org.yyok.oauth.repository;

import java.util.List;
import java.util.Map;
import org.yyok.bean.*;
import org.yyok.oauth.bean.User;

public interface UserMapper {

    List<User> getByMap(Map<String, Object> map);
    User getById(Integer id);
    Integer create(User user);
    int update(User user);
    User getByUserName(String userName);
}