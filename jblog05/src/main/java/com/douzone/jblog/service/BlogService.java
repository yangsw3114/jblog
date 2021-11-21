package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;


@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	public List<BlogVo> getSite() {
		return blogRepository.findAll();
	}
	
	public boolean update(BlogVo blogVo) {
		return blogRepository.update(blogVo);
	}
	

}
