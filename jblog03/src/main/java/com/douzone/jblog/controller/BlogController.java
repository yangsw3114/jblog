package com.douzone.jblog.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.exception.FileUploadException;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;



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
	private FileUploadService fileUploadService;
	
	@RequestMapping({"", "/main"})
	public String main() {
		BlogVo vo = blogService.getSite();
		servletContext.setAttribute("blog", vo);

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
		servletContext.setAttribute("blog", blog);
		
		return "redirect:/" + blogId + "/admin";
	}	
	
	
	@RequestMapping("/admin")
	public String adminBasic(@PathVariable("blogId") String blogId) {
		System.out.println("test" +  blogId);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("blogId") String blogId, Model model){
		List<CategoryVo> vo = categoryService.getCategory(blogId);
		model.addAttribute("category", vo);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("/admin/category/add")
	public String categoryAdd(@PathVariable("blogId") String blogId) {
		
		return "redirect:/" + blogId + "/admin/category";
	}
	
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("blogId") String blogId){
		return "blog/blog-admin-write";
	}
	
}
