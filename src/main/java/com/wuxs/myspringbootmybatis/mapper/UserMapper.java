package com.wuxs.myspringbootmybatis.mapper;

import com.wuxs.myspringbootmybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User>   userList();
}
