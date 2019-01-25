package com.springboot.mapper;

import com.springboot.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
	List<Role> findByUserName(String userName);
}
