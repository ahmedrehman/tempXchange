package ch.ahmed.tests.coffeeshoporders.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
	private long id;
	private List<OrderItem> items = new ArrayList<OrderItem>();

	public Order() {

	}

	public static Order createOrder(long id) {
		Order order = new Order();
		order.id = id;
		return order;
	}

	public OrderItem addOrderItem(ProductVariant productVariant, List<ProductExtra> extras, int count) {
		OrderItem item = new OrderItem(getItems().size() + 1, productVariant, extras, count);
		getItems().add(item);
		return item;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

}
