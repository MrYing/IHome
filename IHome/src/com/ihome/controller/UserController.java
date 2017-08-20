package com.ihome.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ihome.dao.UserDao;
import com.ihome.entity.User;
import com.ihome.utils.MD5Util;
import com.mysql.jdbc.Util;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserDao userDao;

	@RequestMapping("/findUser.do")
	public String find(Model model) {
		List<User> users = userDao.findAll();
		model.addAttribute("users",users);
		return "user/user_list";
	}
	
	@RequestMapping("/addUser.do")
	public String addUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		int viewFlage = Integer.valueOf(request.getParameter("view"));
		int id = Integer.valueOf(request.getParameter("uid"));
		if(viewFlage==1) {
			User user = userDao.findById(id);
			session.setAttribute("user", user);
		}
		session.setAttribute("viewFlage", viewFlage);
		return "user/addUser";
	}
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@RequestMapping("/register")
	public String register(User user) {
		user.setName(user.getName());
		user.setPassword(MD5Util.getEncryptedPwd(user.getPassword()));
		user.setPhone(user.getPhone());
		user.setAddress(user.getAddress());
		user.setRole(1);
		user.setCreateTime(new Date());
		
		boolean addUserBoolean  = userDao.save(user);
		
		return "redirect:/login/login.do";
	}
	
	@RequestMapping("/updateUser.do")
	public String updateUser(HttpServletRequest request) {
		return "/user/updateUser";
	}
	
	@RequestMapping("/submitUpdate.do")
	public String submitUpdate(User user) {
		user.setPhone(user.getPhone());
		user.setAddress(user.getAddress());
		boolean addUserBoolean  = userDao.updateUser(user);
		return "redirect:/user/findUser.do";
	}
	
}
