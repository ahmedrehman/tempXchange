package ch.ahmed.tests.coffeeshoporders.data;

import java.util.ArrayList;
import java.util.List;

public class ProductVariant {
	private String name;
	private ProductCategory category;
	private String label;
	private double price;
	private List<ProductExtra> awailableExtras= new ArrayList<ProductExtra>();

	public ProductVariant() {
	}

	public ProductVariant(String name, ProductCategory category, String label, double price,
			List<ProductExtra> awailableExtras) {
		super();
		this.name = name;
		this.category = category;
		this.label = label;
		this.price = price;
		this.awailableExtras.addAll(awailableExtras);
	}

	public ProductExtra findProductExtraByName(String name) {
		if(name==null)return null;
		return getAwailableExtras().stream()
				.filter(prodExtra->prodExtra.getName()!=null && prodExtra.getName().equals(name))
				.findFirst()
				.orElse(null);
	}
	
	public boolean isBeverage() {
		return getCategory()!=null && getCategory()==ProductCategory.BEWERAGE;
	}
	
	public boolean isSnack() {
		return getCategory()!=null && getCategory()==ProductCategory.SNACK;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
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

	public List<ProductExtra> getAwailableExtras() {
		return awailableExtras;
	}

	public void setAwailableExtras(List<ProductExtra> awailableExtras) {
		this.awailableExtras = awailableExtras;
	}

}
