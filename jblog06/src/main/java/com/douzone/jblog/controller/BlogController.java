package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.exception.FileUploadException;
import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;


@Auth
@Controller
@RequestMapping("/{blogId:(?!assets).*}") 
// DispatcherServlet에서 jblog03/assets/~~ url 매핑되는것을 막기 위해
//(?!assets).*을 사용하여 assets를 걸러낸다.
//걸러내면 sping-servlet.xml에 장착해둔 디폴트서블릿에서 assets의 css파일의 정척파일을 처리한다.
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	
	@RequestMapping({"", "/{categoryno}", "/{categoryno}/{postNo}"})
	public String main(@PathVariable("blogId") String blogId, @PathVariable("categoryno") Optional<Long> categoryNo,
			@PathVariable("postNo") Optional<Long> postNo, Model model) {
		
		
		List<BlogVo> blog = (List<BlogVo>) servletContext.getAttribute("blog");	
		
		for(BlogVo bg : blog) {
			if(blogId.equals(bg.getId())) {
				servletContext.setAttribute("blogbyId", bg);
			}
		}
		
		List<CategoryVo> cgyvo = categoryService.getCategory(blogId);
		servletContext.setAttribute("category", cgyvo);
		
		Long cgyNo = null;
		if(categoryNo.isPresent()) {
			cgyNo = categoryNo.get();
		}
		else {
			cgyNo = categoryNo.orElse(1L); //값이 없다면 3L 리턴
		}	
		List<PostVo> postvo = postService.getPost(cgyNo);
		servletContext.setAttribute("post", postvo);

		Long postno = null;
		if(postNo.isPresent()) {
			postno = postNo.get();
			PostVo postvo2 = postService.getOnePost(postno);
			model.addAttribute("post2", postvo2); //servletContext로 넣으면 서버 끝날때까지 "post2"값이 저장되어있어서 최신화가 안됨 그래서 모델로 바꿈
			//servletContext는 서버시작~서버끝 까지 살아남아있다.
		}
		else {
			postno = postNo.orElse(1L); //값이 없다면 1L 리턴
		}

		return "blog/blog-main"; // WEB-INF/views/bolg/blog-main.jsp
	}
	
	@RequestMapping("/admin/update")
	public String main(@PathVariable("blogId") String blogId, BlogVo blog, @RequestParam("logofile") MultipartFile logofile) {
		try {
			String logo = fileUploadService.restoreImage(logofile);
			
			blog.setLogo(logo);
		} catch(FileUploadException ex) {
			//LOGGER.info("Admin Main Update:" + ex);
		}
		
		blogService.update(blog);
		System.out.println(blog.getTitle() + blog.getId());
		servletContext.setAttribute("blogbyId", blog);
		
		return "redirect:/" + blogId + "/admin";
	}	
	
	@Auth
	@RequestMapping("/admin")
	public String adminBasic(@PathVariable("blogId") String blogId) {

		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value = "/admin/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable("blogId") String blogId, Model model, HttpSession session){
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		List<CategoryVo> vo = categoryService.getCategory(blogId);
		int i =0;
		for(CategoryVo vv : vo) {
			int postCount = categoryService.getPost(vv.getNo());
			vo.get(i).setCount(postCount);
			i++;
		}
		model.addAttribute("category", vo);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value = "/admin/category", method=RequestMethod.POST)  //카테고리 추가
	public String categoryAdd(@PathVariable("blogId") String blogId, CategoryVo categoryVo) {
		categoryVo.setblog_id(blogId);
		categoryService.add(categoryVo);
		return "redirect:/" + blogId + "/admin/category";
	}
	
	@RequestMapping("/admin/category/delete/{no}")
	public String delete(@PathVariable("blogId") String blogId, @PathVariable("no") Long CategoryNo) {
		CategoryVo vo = new CategoryVo();
		vo.setblog_id(blogId);
		vo.setNo(CategoryNo);
		categoryService.deleteContent(vo);
		return "redirect:/" + blogId + "/admin/category";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable("blogId") String blogId, Model model){

		List<CategoryVo> vo = categoryService.getCategory(blogId);
		model.addAttribute("categorySelectBox", vo);
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value = "/admin/write", method=RequestMethod.POST)
	public String post(@PathVariable("blogId") String blogId, PostVo postVo) {
		postService.write(postVo);
		return "redirect:/" + blogId;
	}
	
	@RequestMapping("/admin/post/delete/{cgyno}/{no}")
	public String postdelete(@PathVariable("blogId") String blogId, @PathVariable("cgyno") Long cgyno, @PathVariable("no") Long PostNo) {

		postService.Postdelete(PostNo);
		return "redirect:/" + blogId + "/" + cgyno;
	}

	
}
