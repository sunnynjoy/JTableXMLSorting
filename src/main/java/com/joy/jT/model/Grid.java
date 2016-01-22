package com.joy.jT.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Grid {

	private List<String> column;

	public Grid() {
		super();
	}
	
	@XmlElement(name = "column")
	public void setColumn(List<String> column) {
		this.column = column;
	}

	public List<String> getColumn() {
		return column;
	}
}
