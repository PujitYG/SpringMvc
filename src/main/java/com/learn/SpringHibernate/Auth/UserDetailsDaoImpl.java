package com.learn.SpringHibernate.Auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.learn.SpringHibernate.RolesPermission.Roles;

@Repository("fake")
public class UserDetailsDaoImpl implements IUserDetailsDao {

	@Autowired
	PasswordEncoder pe;
	
	private List<UserDetails> getDetails() { 
		
		return Arrays.asList(
				ApplicationUserDetails.Builder()
				.username("pujit")
				.password(pe.encode("password123"))
				.authorities(Roles.ADMIN.getPermission())
				.build(),
				ApplicationUserDetails.Builder()
				.username("chaitra")
				.password(pe.encode("password123"))
				.authorities(Roles.USER.getPermission())
				.build(),
				ApplicationUserDetails.Builder()
				.username("abhishek")
				.password(pe.encode("password123"))
				.authorities(Roles.USER.getPermission())
				.build()
			);
	}
	
	@Override
	public UserDetails getUserDetailsByName(String username) {
		
		if(username == null) {
			return null;
		}
		
		return getDetails()
				.stream()
				.filter(userDetails -> userDetails.getUsername().equalsIgnoreCase(username))
				.findFirst()
				.orElse(null);

	}

}
