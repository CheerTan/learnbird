package com.springboot.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 维护着一个原子类型的Interger对象，用于统计在线session的数量
 * 完成session定义后需将其注入SecurityManager中
 */
public class ShiroSessionListener implements SessionListener {
	private final AtomicInteger sessionCount=new AtomicInteger(0);

	@Override
	public void onStart(Session session){
		sessionCount.incrementAndGet();
	}

	@Override
	public void onStop(Session session){
		sessionCount.decrementAndGet();
	}

	@Override
	public void onExpiration(Session session){
		sessionCount.decrementAndGet();
	}
}
