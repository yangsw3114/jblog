package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;


@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVo> getCategory(String id){
		
		return categoryRepository.findByBlogId(id);
	}
	
	
	public void add(CategoryVo vo) {
		categoryRepository.insert(vo);
	}
	
	public boolean deleteContent(CategoryVo vo) {
		int count = categoryRepository.delete(vo);
		return count == 1;
	}
	
	public CategoryVo getCategory_overlap(String name) {
		return categoryRepository.findByName(name);
	}
	
	public int getPost(Long cgyNo) {
		return categoryRepository.joinPost(cgyNo);
	}
}
