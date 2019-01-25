package com.springboot.mapper;

import com.springboot.pojo.Permission;
import com.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserPermissionMapper {
	List<Permission> findByUserName(String userName);
}
