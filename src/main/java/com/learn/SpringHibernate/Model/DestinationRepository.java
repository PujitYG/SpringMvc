package com.learn.SpringHibernate.Model;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.SpringHibernate.Entities.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
	
	
	
	

}
