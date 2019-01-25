package com.springboot.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequiresPermissions("user:user")
	@RequestMapping("list")
	public String userList(Model model){
		model.addAttribute("value","get user");
		return "user";
	}
	@RequiresPermissions("user:add")
	@RequestMapping("add")
	public String userAdd(Model model){
		model.addAttribute("value","add user");
		return "user";
	}
	@RequiresPermissions("user:delete")
	@RequestMapping("delete")
	public String userDelete(Model model){
		model.addAttribute("value","delete user");
		return  "user";
	}
}
