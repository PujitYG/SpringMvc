package com.learn.SpringHibernate.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserDetailsDao userDetailsDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = userDetailsDao.getUserDetailsByName(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username+" Not Found");
		}
		
		return user;
	}

}
