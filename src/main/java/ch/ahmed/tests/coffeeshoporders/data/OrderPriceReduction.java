package ch.ahmed.tests.coffeeshoporders.data;

public class OrderPriceReduction {
	
	private int nr;
	private double priceReduction;
	private String reason;
	
	public OrderPriceReduction(int nr, double priceReduction, String reason) {
		super();
		this.nr = nr;
		this.priceReduction = priceReduction;
		this.reason = reason;
	}
	
	public int getNr() {
		return nr;
	}
	public void setNr(int nr) {
		this.nr = nr;
	}
	public double getPriceReduction() {
		return priceReduction;
	}
	public void setPriceReduction(double priceReduction) {
		this.priceReduction = priceReduction;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
