package com.learn.SpringHibernate.Configuration.Private;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.learn.SpringHibernate.Beans.A;

@EnableWebMvc
@ComponentScan(basePackages = "com.learn.SpringHibernate")
@Configuration
@EnableRetry  
@PropertySource("/WEB-INF/a.properties")
public class SpringContextConfiguration {
	
	@Bean("a")
	public A a() {
	
		return new A();
	}
	

	
    @Bean    
    public InternalResourceViewResolver viewResolver() {    
        InternalResourceViewResolver viewResolver    
                          = new InternalResourceViewResolver();    
        viewResolver.setViewClass(JstlView.class);    
        viewResolver.setPrefix("/WEB-INF/views/");    
        viewResolver.setSuffix(".jsp");    
        return viewResolver;    
    }    

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {      
         return new MethodValidationPostProcessor();
    }


	
	
}
