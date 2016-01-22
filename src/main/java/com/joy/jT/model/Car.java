package com.joy.jT.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "make", "model", "year" })
@XmlRootElement(name = "car")
public class Car {

	private String make;
	private String model;
	private int year;

	public Car() {
		super();
	}
	
	@XmlElement(name = "make")
	public void setMake(String make) {
		this.make = make;
	}

	public String getMake() {
		return make;
	}


	@XmlElement(name = "model")
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getModel() {
		return model;
	}
	
	@XmlElement(name = "year")
	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}
}
