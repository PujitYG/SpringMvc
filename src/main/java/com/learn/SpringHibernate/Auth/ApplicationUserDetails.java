package com.learn.SpringHibernate.Auth;

import java.util.Collection;

import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Collection<? extends GrantedAuthority> authorities;
	private String password;
	private String username;
	private boolean accountNonExpired;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private boolean accountNonLocked;
	
	public static class UserDetailsBuilder{
		private Collection<? extends GrantedAuthority> authorities;
		private String password;
		private String username;
		private boolean accountNonExpired = true;
		private boolean credentialsNonExpired = true;
		private boolean enabled = true;
		private boolean accountNonLocked = true;
		
		public UserDetailsBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = authorities;
			return this;
		}
		public UserDetailsBuilder password(String password) {
			this.password = password;
			return this;

		}
		public UserDetailsBuilder username(String username) {
			this.username = username;
			return this;

		}
		public UserDetailsBuilder accountNonExpired(boolean accountNonExpired) {
			this.accountNonExpired = accountNonExpired;
			return this;
		}
		public UserDetailsBuilder credentialsNonExpired(boolean credentialsNonExpired) {
			this.credentialsNonExpired = credentialsNonExpired;
			return this;

		}
		public UserDetailsBuilder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}
		
		public UserDetailsBuilder accountNonLocked(boolean accountNonLocked) {
			this.accountNonLocked =accountNonLocked;
			return this;
		}
		
		public UserDetails build() {
			return new ApplicationUserDetails(authorities, password,
					username, accountNonExpired, 
					credentialsNonExpired, enabled, accountNonLocked);
		}
		
	}
	
	public static UserDetailsBuilder Builder() {
		return new UserDetailsBuilder();
		
	}
	


	public ApplicationUserDetails(Collection<? extends GrantedAuthority> authorities, String password, String username,
			boolean accountNonExpired, boolean credentialsNonExpired, boolean enabled, boolean accountNonLocked) {
		super();
		this.authorities = authorities;
		this.password = password;
		this.username = username;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.accountNonLocked = accountNonLocked;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

}
