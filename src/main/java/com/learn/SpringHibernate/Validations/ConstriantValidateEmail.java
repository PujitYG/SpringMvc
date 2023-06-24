package com.learn.SpringHibernate.Validations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class ConstriantValidateEmail implements ConstraintValidator<ValidateEmail, String> {

	List<String> emailExtensions;
	
	public ConstriantValidateEmail() {
		super();
		emailExtensions = new ArrayList<String>();
		emailExtensions.add("gmail.com");
		emailExtensions.add("outlook.com");
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {		
//		context.disableDefaultConstraintViolation();
//		
//		context.buildConstraintViolationWithTemplate("Email is not valid");
		
		if(!value.contains("@")) return false;
		
		String[] emailParts = value.split("@");
		
		boolean part2 = this.emailExtensions.stream().anyMatch(emailParts[1]::equalsIgnoreCase);
//		System.out.println(part2);
		return part2;
	}

}
