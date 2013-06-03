package com.datacart.controller;

import com.datacart.webservice.resources.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Dmitriy Sinenkiy - Team International
 * @since 2.0
 */
@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/userList")
	public List<String> getUserNames() {
		return userService.getUserNames();
	}
}
