package com.learn.SpringHibernate.Auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


public interface IUserDetailsDao {
	
	UserDetails getUserDetailsByName(String username);

}
