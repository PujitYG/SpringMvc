package com.learn.SpringHibernate.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DESTINATION_TABLE")
public class Destination {
	
	@Id
	@GeneratedValue
	Integer destinationId;
	
	String destination;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Flight_ID")
	Flight flight;

	
	

	public Integer getDestinationId() {
		return destinationId;
	}




	public void setDestinationId(Integer destinationId) {
		this.destinationId = destinationId;
	}




	public String getDestination() {
		return destination;
	}




	public void setDestination(String destination) {
		this.destination = destination;
	}




	public Flight getFlight() {
		return flight;
	}




	public void setFlight(Flight flight) {
		this.flight = flight;
	}




	@Override
	public String toString() {
		return "Destination [destinationId=" + destinationId + ", destination=" + destination + "]";
	}
	
	
	
}
