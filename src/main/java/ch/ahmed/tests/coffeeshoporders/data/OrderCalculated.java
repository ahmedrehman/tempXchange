package ch.ahmed.tests.coffeeshoporders.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderCalculated implements Serializable {
	private Order order;
	private List<OrderPriceReduction> priceReductions = new ArrayList<OrderPriceReduction>();
	private double totalCost;
	private double totalReduction;
	private double total;

	public OrderCalculated() {

	}

	public OrderCalculated(Order order) {
		this.order = order;
	}

	public OrderPriceReduction addPriceReduction(double priceReduction, String reason) {
		OrderPriceReduction reduction = new OrderPriceReduction(getPriceReductions().size() + 1, priceReduction,
				reason);
		getPriceReductions().add(reduction);
		return reduction;
	}

	public List<OrderPriceReduction> getPriceReductions() {
		return priceReductions;
	}

	public void setPriceReductions(List<OrderPriceReduction> priceReductions) {
		this.priceReductions = priceReductions;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalReduction() {
		return totalReduction;
	}

	public void setTotalReduction(double totalReduction) {
		this.totalReduction = totalReduction;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
