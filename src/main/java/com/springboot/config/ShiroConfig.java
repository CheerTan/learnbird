package com.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.springboot.shiro.ShiroRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.DependsOn;

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
	public SimpleCookie rememberMeCookie(){
		//设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberme">
		SimpleCookie cookie=new SimpleCookie("rememberme");
		//set expired time, measure time by seconds
		cookie.setMaxAge(86400);
		return cookie;
	}
	public RememberMeManager rememberMeManager(){
		CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
//		cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		return cookieRememberMeManager;
	}
	public RedisManager redisManager(){
		RedisManager redisManager=new RedisManager();
		return redisManager;
	}
	public RedisCacheManager redisCacheManager(){
		RedisCacheManager redisCacheManager=new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}
	@Bean
	public SecurityManager securityManager(){
		//Configure SecurityManager, and inject it into shiroRealm
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		securityManager.setRememberMeManager(rememberMeManager());
		securityManager.setCacheManager(redisCacheManager());
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
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	@Bean
	public ShiroDialect shiroDialect(){
		return new ShiroDialect();
	}
}
