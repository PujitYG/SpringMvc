package com.learn.SpringHibernate.Model;

import java.sql.ResultSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import javax.persistence.criteria.Order;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.learn.SpringHibernate.Beans.UpdatesAnalytics;
import com.learn.SpringHibernate.Entities.Destination;
import com.learn.SpringHibernate.Entities.Flight;



@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
	
//	@Query("Select f from Flight f where f.id in :lis")
	List<Flight> findFirst10ByCityIgnoreCase(String name, Sort sort);
	
	Page<Flight> findAll(Pageable p);
	
	@Query(value = "Select f.name,f.updates from Flight f where city=?1", nativeQuery = true)
//	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0"),
//		@QueryHint(name="org.hibernate.cacheable", value = "true")})
	List<Object[]> getFlightsusing(String city, Pageable p);
	
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Modifying
	@Query(value = "update Flight f set updates=updates+1 where id=?1")
	void updateFlightUpdates(Integer id);
	
	
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="20000")})
//		@QueryHint(name="org.hibernate.cacheable", value = "true")})
	Optional<Flight> findById(Integer value);
	
	List<Flight> findByIdAndNameIgnoreCaseAndCityIgnoreCaseOrderByNameDesc(Integer id,String name,String city);
	
	Stream<Flight> findByidIn(List<Integer> values);
	
	
//	void deleteByIdIn(List<Integer> lis);
	
//	@org.springframework.transaction.annotation.Transactional(rollbackFor = {Exception.class,NoSuchElementException.class}, isolation = Isolation.READ_COMMITTED)
	@Modifying
	@Query("delete from Flight where id in :lis")
	void delByIdIn(@Param("lis") List<Integer> lis);
	
	
	@Transactional(propagation = Propagation.NESTED)
	void deleteByNameIn(String... name);
	
//	@Override
	void deleteById(Integer name);
	
//	Transactional(pro)
	Optional<Flight> findByName(String name);
	
	
	Flight findFirstByNameStartingWithAndCityEndingWithIgnoreCaseAndIdNull(String flightName, String city);
	
	@Procedure
	List<Flight> callProc(@Param("id_")Integer id);
	
}
