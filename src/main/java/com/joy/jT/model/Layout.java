package com.joy.jT.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "layout")
@XmlAccessorType( XmlAccessType.FIELD )
public class Layout {

	private List<Grid> grid;
	Menu menu;

	public Layout() {
		super();
	}

	public List<Grid> getGrid() {
		return grid;
	}

	public void setGrid(List<Grid> grid) {
		this.grid = grid;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
