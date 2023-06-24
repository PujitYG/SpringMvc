package com.learn.SpringHibernate.Beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


public class B {
	
	String value;
	
	public B() {}
	
	
	

	public B(String value) {
		super();
		this.value = value;
	}




	@Override
	public String toString() {
		return "B [value=" + value + "]";
	}



	
	

}
