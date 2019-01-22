package com.springboot.shiro;

import com.springboot.mapper.UserMapper;
import com.springboot.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserMapper userMapper;
	/**
	 * Get user roles and permission
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}
	/**
	 * Login auth
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)throws AuthenticationException{
		String username=(String) token.getPrincipal();
		String password=new String((char [])token.getCredentials());
		System.out.println("User:"+username+"AuthenticationInfo:");
		User user=userMapper.findByUserName(username);
		if(!password.equals(user.getPassword())){
			throw new IncorrectCredentialsException("Check user or password");
		}
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user,password,getName());
		return info;

	}
}
