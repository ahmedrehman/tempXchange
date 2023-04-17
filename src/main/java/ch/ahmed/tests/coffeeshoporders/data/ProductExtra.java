package ch.ahmed.tests.coffeeshoporders.data;

import java.util.List;

public class ProductExtra {
	
	private String name;
	private String label;
	private double price;
	
	public ProductExtra() {}
	public ProductExtra(String name, String label, double price) {
		super();
		this.name = name;
		this.label = label;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
