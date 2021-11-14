package com.douzone.jblog.repository;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.exception.FileUploadException;
import com.douzone.jblog.vo.BlogVo;



@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BlogVo> findAll() throws FileUploadException{		
		return sqlSession.selectList("blog.findAll");			
	}

	public boolean update(BlogVo vo) {
		int count = sqlSession.update("blog.update", vo);
		return count==1;
	}
	
	
	
	
}
