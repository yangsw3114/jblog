package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;
	
	
	public boolean insert(PostVo postVo) {
		int count = sqlSession.insert("post.insert", postVo);
		return count == 1;
	}


	public List<PostVo> findByCgyno(Long category_no) {
		return sqlSession.selectList("post.findByCgyno",category_no);
	}
	
	public PostVo findByNo(Long no) {
		return sqlSession.selectOne("post.findByNo", no);
	}
	
	public int delete(Long no) {
		return sqlSession.delete("post.delete", no);
	}

}
