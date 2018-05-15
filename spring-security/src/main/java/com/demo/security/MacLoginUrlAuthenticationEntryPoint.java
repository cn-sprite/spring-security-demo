package com.demo.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class MacLoginUrlAuthenticationEntryPoint  implements AuthenticationEntryPoint,InitializingBean{

	public void afterPropertiesSet() throws Exception {
		
		
	}

	public void commence(HttpServletRequest arg0, HttpServletResponse response, AuthenticationException arg2)
			throws IOException, ServletException {
		// redirect to login page. Use https if forceHttps true
        response.setContentType("application/json;charset=utf-8");
           PrintWriter out = response.getWriter();
           StringBuffer sb = new StringBuffer();
           sb.append("{\"code\":\"-1000\",\"msg\":\"");
           sb.append("未登陆!");
           sb.append("\"}");
           out.write(sb.toString());
           out.flush();
           out.close();
	}

}
