package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.CategoryVo;


@RestController("categoryApiController")
@RequestMapping("/category/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
		
	@GetMapping("/checkcategoryname")
	public JsonResult checkcategoryname(@RequestParam(value="categoryname", required=true, defaultValue="") String name) {
		CategoryVo categoryVo = categoryService.getCategory_overlap(name);
		return JsonResult.success(categoryVo != null);
	}
}
