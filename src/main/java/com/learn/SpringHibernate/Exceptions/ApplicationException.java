package com.learn.SpringHibernate.Exceptions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handleException1(MethodArgumentNotValidException e) {
		System.out.println(e.getBindingResult());
		
		
		return "Method Argument not valid except";
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException2(Exception e) {
		e.printStackTrace();
		return "YEP EXCEPTION BANTU Exceasda WOAH CHILD CLASS IDU -> "+e.getMessage();
	}
	
	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public String handleException3(SQLException e) {
		e.printStackTrace(); 
		return "SQL EXCEPTIOn";
	}
	
	

	

	
}
