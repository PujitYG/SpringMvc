package com.learn.SpringHibernate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.learn.SpringHibernate.Entities.Flight;
import com.learn.SpringHibernate.Model.FlightRepository;

@Component
public class Admin2 {
	
	@Autowired
	FlightRepository flightRepository;
	
	
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public void propogationCheck(boolean value) throws Exception {
		Flight f1 = new Flight();
		f1.setUpdates(1);
		f1.setName("nam new nested 120000");
		f1.setCity("city");
		
		flightRepository.save(f1);
		if(value) {
			throw new Exception("This is new");
		}
	}

}
