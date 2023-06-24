package com.learn.SpringHibernate.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.learn.SpringHibernate.Beans.B;
import com.learn.SpringHibernate.RolesPermission.Roles;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManagerBuilder builder;
	

//    @Bean
//    public AuthenticationManager authManager() throws Exception {
////    	AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
//    	builder.authenticationProvider(daoAuthenticationProvider());
//    	return builder.build();
//    }
//	

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		HttpSessionCsrfTokenRepository
//		

		
//		System.out.println("ININI");
		 http
		 .csrf()
		 .csrfTokenRepository(csrfTokenRepository())
		 .and()
         .authorizeRequests()
         .anyRequest()
         .authenticated()
         .and()
//         .permitAll();
//         .authenticationProvider(daoAuthenticationProvider())
//         .formLogin()
//         .defaultSuccessUrl("/api/v1/test");
		 .httpBasic();
//		 authManager();

		return http.build();
	}
	
    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-CSRF-TOKEN");
        repository.setSessionAttributeName("APPLICATION_CSRF");
        return repository;
    }
	
//	@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth
//            .inMemoryAuthentication()
//               .passwordEncoder(NoOpPasswordEncoder.getInstance())
//               .withUser("user").password("password").roles("USER");
//    } 
//	
//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails user1 = User.builder()
//				.username("user")
//				.password(pe.encode("password123"))
//				.authorities(Roles.USER.getPermission())
//				.build();
//		
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(pe.encode("password123"))
//				.authorities(Roles.ADMIN.getPermission())
//				.build();
//		
//		return new InMemoryUserDetailsManager(user1,admin);
//	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService());
//	}
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(encoder());
    	return provider ;
    }
    

    
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//        return auth.build();
//    }
    
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) {
//    	auth.authenticationProvider(daoAuthenticationProvider());
//    }

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(10);
	}
	
 

}
