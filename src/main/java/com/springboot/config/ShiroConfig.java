package com.springboot.config;

import com.springboot.shiro.ShiroRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import java.util.LinkedHashMap;


@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
		//Set securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//Set login url
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		LinkedHashMap<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
		//Don't block static resources,execute rules by order.
		filterChainDefinitionMap.put("/css/**","anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		//shiro help us finish logout
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	@Bean
	public SecurityManager securityManager(){
		//Configure SecurityManager, and inject it into shiroRealm
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		return securityManager;
	}
	@Bean(name="lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		//shiro lifecycle processor
		return new LifecycleBeanPostProcessor();
	}
	@Bean
	public ShiroRealm shiroRealm(){
		ShiroRealm shiroRealm =new ShiroRealm();
		return shiroRealm;
	}
}
