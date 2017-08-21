package com.ihome.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ihome.dao.UserDao;
import com.ihome.entity.User;
import com.ihome.utils.MD5Util;
import com.ihome.utils.Util;



@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Resource
	private UserDao userDao;
	
	@RequestMapping("/toLogin.do")
	public String toLogin() {
		return "main/login";
	}
	
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response) {
			response.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			User user = userDao.findByName(name);
			if(user != null) {
				HttpSession session = request.getSession();
				if(password != null && MD5Util.validPassword(password, user.getPassword())) {
					session.setAttribute("user", user);
					return "/index/";
				}
			}
			return Util.getSuccessScript("用户不存在或密码错误","/login/toLogin.do");
	}
	
}
