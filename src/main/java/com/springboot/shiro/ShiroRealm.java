package com.springboot.shiro;

import com.springboot.mapper.UserMapper;
import com.springboot.mapper.UserPermissionMapper;
import com.springboot.mapper.UserRoleMapper;
import com.springboot.pojo.Permission;
import com.springboot.pojo.Role;
import com.springboot.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserPermissionMapper userPermissionMapper;
	/**
	 * Get user roles and permission
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		User user= (User)SecurityUtils.getSubject().getPrincipal();
		String username=user.getUserName();
		System.out.println("User is "+username+" ShiroRealm.doGet");
		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
		//Get user roles lists
		List<Role> roleList=userRoleMapper.findByUserName(username);
		Set<String> roleSet=new HashSet<String>();
		for(Role r:roleList){
			roleSet.add(r.getName());
		}
		simpleAuthorizationInfo.setRoles(roleSet);
		//Get user permission lists;
		List<Permission> permissionList=userPermissionMapper.findByUserName(username);
		Set<String> permissionSet=new HashSet<String>();
		for(Permission p:permissionList){
			permissionSet.add(p.getName());
		}
		simpleAuthorizationInfo.setStringPermissions(permissionSet);
		return simpleAuthorizationInfo;
	}
	/**
	 * Login auth
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)throws AuthenticationException{
		String username=(String) token.getPrincipal();
		String password=new String((char [])token.getCredentials());
		System.out.println("User:"+username+"AuthenticationInfo:");
		System.out.println("Input pwd is "+password);
		User user=userMapper.findByUserName(username);
		System.out.println("user is "+user.getUserName());
		System.out.println("password is "+user.getPassword());
		if(!password.equals(user.getPassword())){
			throw new IncorrectCredentialsException("Check user or password");
		}
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user,password,getName());
		return info;

	}
}
