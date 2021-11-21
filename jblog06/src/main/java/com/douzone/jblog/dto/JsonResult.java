package com.douzone.jblog.dto;

public class JsonResult {
	private String result; // success 와 fail 
	private Object data; // success일 경우 값 설정
	private String message; //fail일 경우 값 설정
	
	private JsonResult() {}
	
	private JsonResult(Object data) {  // 성공일 경우
		result = "success";
		this.data = data;
		message = null;
	}
	
	private JsonResult(String message) { // 실패일 경우
		result = "fail";
		data = null;
		this.message = message;
	}
	
	public static JsonResult success(Object data) {
		return new JsonResult(data);
	}
	
	public static JsonResult fail(String message) {
		return new JsonResult(message);
	}
	
	public String getResult() {
		return result;
	}
	
	public Object getData() {
		return data;
	}
	
	public String getMessage() {
		return message;
	}
}
