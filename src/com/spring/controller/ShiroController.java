package com.spring.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class ShiroController {
	
	@RequestMapping("/login")
	public String login(@RequestParam("username")String username,@RequestParam("password")String password,Model model){
		
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (UnknownAccountException uae) { 
			model.addAttribute("message", "用户名未找到...");
			System.out.println(uae.getMessage());
            return "login";
        } catch (IncorrectCredentialsException ice) {
        	System.out.println(ice.getMessage());
        	model.addAttribute("message", "密码不正确...");
        	return "login";  
        } catch (LockedAccountException lae) { 
        	System.out.println(lae.getMessage());
        	model.addAttribute("message", "账号被锁定...");
        	return "login";  
        } 
		return "success";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		return "login";
	}
}
