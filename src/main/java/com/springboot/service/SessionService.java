package com.springboot.service;

import com.springboot.pojo.UserOnline;

import java.util.List;

public interface SessionService {
	List<UserOnline> list();
	boolean forceLogout(String sessionId);
}
