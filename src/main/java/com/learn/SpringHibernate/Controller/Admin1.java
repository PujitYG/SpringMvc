package com.learn.SpringHibernate.Controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import com.google.protobuf.Option;
import com.learn.SpringHibernate.Beans.A;
import com.learn.SpringHibernate.Beans.B;
import com.learn.SpringHibernate.Beans.UpdatesAnalytics;
import com.learn.SpringHibernate.DTO.UserRequest;
import com.learn.SpringHibernate.Entities.Destination;
import com.learn.SpringHibernate.Entities.Flight;
import com.learn.SpringHibernate.Model.DestinationRepository;
import com.learn.SpringHibernate.Model.FlightAnalytics;
import com.learn.SpringHibernate.Model.FlightAnalyticsProjection;
import com.learn.SpringHibernate.Model.FlightRepository;
import com.learn.SpringHibernate.RolesPermission.Roles;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order.Direction;



@Controller
//@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(path="api/v1")
@Validated
//@PropertySource("/application.properties")
public class Admin1 {
	
	@Autowired
	Admin2 admin;

	
	@Value("#{a}")
	A a;
	
	@Value("#{a.getIn('okok')+1>1}")
	boolean value;
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	DestinationRepository destinationRepository;
	
	@Autowired
	Environment properties;


	@ResponseBody
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@RequestMapping(value = "check/{value1}",method = {RequestMethod.GET,RequestMethod.POST})
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<String> adminOne(@PathVariable("value1") Long value
			, HttpServletResponse res) throws IOException {
			

		
		return ResponseEntity.ok().body(Roles.ADMIN.name());
	}

	@RequestMapping(value = "check2/{value1}", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminTwo(String value1) {
		return "value1";
	}
	
	@RequestMapping(value = "addUser",method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String adminThree(@RequestHeader Map<String, String> val, @RequestBody @Valid UserRequest str) throws Exception {
		System.out.println(str) ;
		if(str.getId()>40) {
			throw new Exception();
		}
		return "Success";
	}
	@ResponseBody
	@RequestMapping(value="adminfour/{value}", method = RequestMethod.GET)
//	@Retryable(value = LockAcquisitionException.class, maxAttempts = 5, backoff = @Backoff(delay = 3000))
	@org.springframework.transaction.annotation.Transactional(rollbackFor = {Exception.class,NoSuchElementException.class}, isolation = Isolation.SERIALIZABLE)
	public ResponseEntity<String> adminfour(@PathVariable String value) throws Exception{
		
		Flight flight = flightRepository.findById(12).orElse(null);
		
		Thread.sleep(4000);
		
		Integer updates = flight.getUpdates();
		flight.setUpdates(updates+1);
		
		return ResponseEntity.ok("Hello");
	}
	
	@ResponseBody
//	@Retryable(value = LockAcquisitionException.class, maxAttempts = 5, backoff = @Backoff(delay = 3000))
	@RequestMapping(value="adminfive/{value}", method = RequestMethod.GET)
	@org.springframework.transaction.annotation.Transactional(rollbackFor = {Exception.class,NoSuchElementException.class}, isolation = Isolation.SERIALIZABLE)
	public ResponseEntity<String> adminfive(@PathVariable("value") Long value) throws Exception{

		Flight flight = flightRepository.findById(12).orElse(null);

		Integer updates = flight.getUpdates();
		flight.setUpdates(updates+1);
		
		
		return ResponseEntity.ok("Hello 123");
	}
	
	@PostMapping(path = "flighttest")
	@ResponseBody
	public String adminsix(@Valid @RequestBody Flight f) {
		System.out.println(f);
		return "login";
	}
	
	@ResponseBody
	@GetMapping(path = "cookies",consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.TEXT_PLAIN_VALUE)
	public String adminsseven(HttpServletRequest request) {


		String return_val ="one";
		
		return return_val;
	}
	
	@ResponseBody
	@GetMapping(path = "cookies",consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String adminsnine(HttpServletRequest request) {

		String return_val ="two";
		
		return return_val;
	}
	
	@ResponseBody
	@PostMapping(path = "success")
	public String adminseight(HttpServletRequest request) {
		System.out.println(request.getParameter("_csrf"));
		
		
		
		return ((CsrfToken) request
				.getSession()
				.getAttribute("APPLICATION_CSRF")) .getToken();
	}
	
	@ResponseBody
	@GetMapping(path = "test")
	@Transactional(propagation = Propagation.REQUIRED)
	public String test(HttpServletRequest request) throws Exception {
		 
		System.out.println(request.getParameter("_csrf"));
		return ((CsrfToken) request
				.getSession()
				.getAttribute("APPLICATION_CSRF")) .getToken();
	}
	
	
	
	@Recover
	@ResponseBody
	public ResponseEntity<String> recover(CannotAcquireLockException e){
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("Well I tried");
	}
	

}
