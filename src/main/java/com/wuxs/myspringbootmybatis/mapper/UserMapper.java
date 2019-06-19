package com.wuxs.myspringbootmybatis.mapper;

import com.wuxs.myspringbootmybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public int userCheck(String account, String password);
    public int insertUser(User user);
}
