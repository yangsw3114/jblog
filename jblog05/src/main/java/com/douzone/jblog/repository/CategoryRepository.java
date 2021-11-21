package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;


@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> findByBlogId(String blogid) {
		return sqlSession.selectList("category.findByBlogId", blogid);
	}
	
	public boolean insert(CategoryVo vo) {
		int count = sqlSession.insert("category.insert", vo);
		return count == 1;
	}
	
	public int delete(CategoryVo vo){
		return sqlSession.delete("category.delete", vo);
	}
	
	public CategoryVo findByName(String name) {
		
		return sqlSession.selectOne("category.findByName", name);
	}
	
	public int joinPost(Long cgyNo) {
		return sqlSession.selectOne("category.join_post",cgyNo);
	}
}
