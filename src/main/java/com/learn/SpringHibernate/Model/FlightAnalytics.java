package com.learn.SpringHibernate.Model;

public class FlightAnalytics {
	String name;
	Integer updates;
	public FlightAnalytics(String name, Integer updates) {
		super();
		this.name = name;
		this.updates = updates;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUpdates() {
		return updates;
	}
	public void setUpdates(Integer updates) {
		this.updates = updates;
	}
	@Override
	public String toString() {
		return "FlightAnalytics [name=" + name + ", updates=" + updates + "]";
	}
	
	
	
}
