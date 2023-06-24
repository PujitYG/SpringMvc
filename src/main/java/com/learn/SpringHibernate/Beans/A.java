package com.learn.SpringHibernate.Beans;

import javax.persistence.Inheritance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;



public class A {
	
	@Value("1")
	private Integer in;
	
	
	@Value("${check.user}")
	private String st;

	public A() {
	}
	
	private A(Integer in,String st) {
		this.in=in;
		this.st=st;
	}
	
	public Integer getIn(String val) {
		System.out.println(val);
		return in;
	}

	public void setIn(Integer in) {
		this.in = in;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}


	@Override
	public String toString() {
		return "A [in=" + in + ", st=" + st + "]";
	}

	

	

}
