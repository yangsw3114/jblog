package com.douzone.jblog.repository;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.exception.FileUploadException;
import com.douzone.jblog.vo.BlogVo;


@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo findone() throws FileUploadException{		
		BlogVo vo = sqlSession.selectOne("blog.findOne");		
		return vo;		
	}
	
	public boolean update(BlogVo vo) {
		int count = sqlSession.update("blog.update", vo);
		return count==1;
	}
	
	
	
	
}
