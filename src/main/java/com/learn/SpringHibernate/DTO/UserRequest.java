package com.learn.SpringHibernate.DTO;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.learn.SpringHibernate.Validations.ValidateEmail;



public class UserRequest implements Serializable {
	
	@Min(10)
	@Max(300)
	private Integer id;
	private String name;
	private String age;
	
	@NotBlank
	@ValidateEmail
	private String email;

	public UserRequest() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserRequest [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + "]";
	}
	

}
