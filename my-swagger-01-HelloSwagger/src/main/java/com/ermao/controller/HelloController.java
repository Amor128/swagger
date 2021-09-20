package com.ermao.controller;

import com.ermao.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ermao
 * Date: 2021/9/20 19:14
 */
@Api("Hello 控制器")
@RestController
public class HelloController {

	@ApiOperation("获取 hello")
	@GetMapping("/hello")
	public String hello() {
		return "Hello, Swagger!";
	}

	@ApiOperation("获取用户信息")
	@GetMapping("/user")
	public User getUser() {
		User user = new User();
		user.setPassword("12345");
		user.setUsername("ermao");
		return user;
	}

	@PostMapping("/user")
	public String uploadUser(@ApiParam("用户名") @RequestParam("username") String username,
							 @ApiParam("密码") @RequestParam("password") String password) {
		User user = new User();
		user.setPassword(username);
		user.setUsername(password);
		return user.toString();
	}
}
