package ch.ahmed.tests.coffeeshoporders.data;

import java.util.ArrayList;
import java.util.List;

public class Product {
	
	private String name;
	private String label;
	private List<ProductVariant> awailableProductVariants = new ArrayList<ProductVariant>();;
	
	public Product() {}
	public Product(String name, String label, List<ProductVariant> awailableProductVariants) {
		super();
		this.name = name;
		this.label = label;
		this.awailableProductVariants.addAll(awailableProductVariants);
	}
	
	public ProductVariant findProductVariantByName(String name) {
		if(name==null)return null;
		return getAwailableProductVariants().stream()
				.filter(prodVariant->prodVariant.getName()!=null && prodVariant.getName().equals(name))
				.findFirst()
				.orElse(null);
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
	public List<ProductVariant> getAwailableProductVariants() {
		return awailableProductVariants;
	}
	public void setAwailableProductVariants(List<ProductVariant> awailableProductVariants) {
		this.awailableProductVariants = awailableProductVariants;
	}
}
