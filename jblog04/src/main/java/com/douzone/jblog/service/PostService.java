package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	
	public void write(PostVo postVo) {
		postRepository.insert(postVo);
	}
	
	public List<PostVo> getPost(Long category_no){
		return postRepository.findByCgyno(category_no);
	}
	
	public PostVo getOnePost(Long no) {
		return postRepository.findByNo(no);
	}
	
	public boolean Postdelete(Long no) {
		int count =postRepository.delete(no);
		return count == 1;
	}

}
