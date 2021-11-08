package com.douzone.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String desc;
	private String blog_id;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getblog_id() {
		return blog_id;
	}
	public void setblog_id(String blogid) {
		this.blog_id = blogid;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", desc=" + desc + ", blog_id=" + blog_id + "]";
	}
	
	
}
