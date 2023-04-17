package ch.ahmed.tests.coffeeshoporders.data;

import java.util.ArrayList;
import java.util.List;

public class OrderItem {
	
	private int positionNr;
	private ProductVariant item;
	private List<ProductExtra> extras= new ArrayList<ProductExtra>();
	private int count;
	
	public OrderItem() {
	}
	public OrderItem(int positionNr, ProductVariant item, List<ProductExtra> extras, int count) {
		super();
		this.positionNr = positionNr;
		this.item = item;
		if(extras!=null) {
			this.extras.addAll(extras);
		}
		this.count = count;
	}
	public int getPositionNr() {
		return positionNr;
	}
	public void setPositionNr(int positionNr) {
		this.positionNr = positionNr;
	}
	public ProductVariant getItem() {
		return item;
	}
	public void setItem(ProductVariant item) {
		this.item = item;
	}
	public List<ProductExtra> getExtras() {
		return extras;
	}
	public void setExtras(List<ProductExtra> extras) {
		this.extras = extras;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
