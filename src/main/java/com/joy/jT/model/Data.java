package com.joy.jT.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "data")
public class Data {
	private List<Car> car;
	
	public Data() {
		super();
	}

	public List<Car> getCar() {
		return car;
	}

	@XmlElement( name = "car", required = true)
	public void setCar(List<Car> car) {
		this.car = car;
	}
}
