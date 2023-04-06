package com.smw.Backend.Mapper;

import com.smw.Backend.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void save(User user);

    User findByUsername(String username);
}
