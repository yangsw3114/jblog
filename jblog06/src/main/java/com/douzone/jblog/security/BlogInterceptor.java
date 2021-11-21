package com.douzone.jblog.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;



public class BlogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private BlogService blogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		List<BlogVo> blog = (List<BlogVo>) request.getServletContext().getAttribute("blog");

		blog = blogService.getSite();
		request.getServletContext().setAttribute("blog", blog);


		return true;
	}
}