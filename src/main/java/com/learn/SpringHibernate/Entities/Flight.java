 package com.learn.SpringHibernate.Entities;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name="FLIGHT_TABLE")
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedNativeQuery(
		query =  "Select * from Flight f where city=?1",
		name = "Flight.getFlightsusing",
		resultClass = Flight.class)
@NamedStoredProcedureQuery(name = "Flight.callProc", 
procedureName = "get_emp_rec", parameters = {
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "id_", type = Integer.class),
  @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR , name = "v_out_cur",type=ResultSet.class)},
resultClasses = Flight.class)
public class Flight {
	
	@Id
	@GeneratedValue
	@Min(10)
	private Integer id;

	private String name;
	
	private String city;
	
	
	private Integer updates;
	
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Destination> destinations =new ArrayList<Destination>(); 
	
//	@Version
//	private Integer version;
	
	public Flight() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getUpdates() {
		return updates;
	}

	public void setUpdates(Integer updates) {
		this.updates = updates;
	}


	
	

	public List<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}

	
	
	@Override
	public String toString() {
		return "Flight [id=" + id + ", name=" + name + ", city=" + city + ", updates=" + updates + ", destinations="
				+ destinations + "]";
	}



	
	
}
