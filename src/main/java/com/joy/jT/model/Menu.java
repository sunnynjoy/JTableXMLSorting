package com.joy.jT.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "menu")
public class Menu {

	private Button button;

	public Menu() {
		super();
	}
	
	public void setButton(Button button) {
		this.button = button;
	}

	public Button getButton() {
		return button;
	}
}
