package com.demo.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("get")
	@ResponseBody
	public String get() {
		return "get ok";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete() {
		return "delete ok";
	}
	
	@RequestMapping("/admin/set")
	@ResponseBody
	public String set() {
		return "set ok";
	}
	
	 // @GetMapping("/login")
		@RequestMapping("/login")
	    public String login() {
			//return "login";
	        return "redirect:/login_page.html";
	    }
		@Autowired
	    @Qualifier("authenticationManager") // bean id 在 <authentication-manager> 中设置
	    private AuthenticationManager authManager;
	 
	    @Autowired
	    private SessionAuthenticationStrategy sessionStrategy;
	    @Autowired(required = false)
	    private RememberMeServices rememberMeServices;
	 
	    @Autowired(required = false)
	    private ApplicationEventPublisher eventPublisher;

		
		@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	    public String loginPost(String username,String password, BindingResult result,
	            HttpServletRequest request, HttpServletResponse response) {
	        if (!result.hasErrors()) {
	 
	            // 创建一个用户名密码登陆信息
	            UsernamePasswordAuthenticationToken token =
	                new UsernamePasswordAuthenticationToken(username, password);
	 
	            try {
	                // 用户名密码登陆效验
	                Authentication authResult = authManager.authenticate(token);
	 
	                // 在 session 中保存 authResult
	                sessionStrategy.onAuthentication(authResult, request, response);
	 
	                // 在当前访问线程中设置 authResult
	                SecurityContextHolder.getContext().setAuthentication(authResult);
	 
	                // 如果记住我在配置文件中有配置
	                if (rememberMeServices != null) {
	                    rememberMeServices.loginSuccess(request, response, authResult);
	                }
	 
	                // 发布登陆成功事件
	                if (eventPublisher != null) {
	                    eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
	                }
	                return "redirect:/";
	            } catch (AuthenticationException e) {
	                result.reject("authentication.exception", e.getLocalizedMessage());
	            }
	        }
	        return "login";
	    }
	
}
