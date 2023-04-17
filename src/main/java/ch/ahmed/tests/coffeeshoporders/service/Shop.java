package ch.ahmed.tests.coffeeshoporders.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import ch.ahmed.tests.coffeeshoporders.data.Order;
import ch.ahmed.tests.coffeeshoporders.data.OrderCalculated;
import ch.ahmed.tests.coffeeshoporders.data.OrderItem;
import ch.ahmed.tests.coffeeshoporders.data.OrderPriceReduction;
import ch.ahmed.tests.coffeeshoporders.data.Product;
import ch.ahmed.tests.coffeeshoporders.data.ProductExtra;
import ch.ahmed.tests.coffeeshoporders.data.ProductVariant;

public class Shop {

	private final List<Product> products = new ArrayList<Product>();
	private final OrderManager orderManager;

	public Shop() {
		this.orderManager = new OrderManager();
	}

	private static Shop instance;

	public static Shop getInstance() {
		if (instance == null) {
			instance = new Shop();
		}
		return instance;
	}

	public List<Product> getProducts() {
		return products;
	}

	public OrderManager getOrderManager() {
		return orderManager;
	}

	public void initProducts(List<Product> products) {
		if (products == null)
			return;
		this.products.addAll(products);
	}

	public ProductVariant findProductVariantByName(String name) {
		return products.stream().map(
				prod -> 
				prod.findProductVariantByName(name)
				).filter(x->x!=null).
				findFirst().orElse(null);
	}
    
	public OrderCalculated calculateOrder(Order order, int customerStamps) {
		if (order == null) {
			return null;
		}
		OrderCalculated calcOrder= new OrderCalculated(order);
		double total=0;
		double totalMinus=0; 
		// free beverages

		Integer countBeverages = order.getItems().stream().filter(orderItem -> orderItem.getItem().isBeverage())
				.mapToInt(orderItem -> orderItem.getCount()).sum();

		// Free Drinks
		int freebeveragesTotal = (customerStamps + countBeverages.intValue()) / 5;
		if (freebeveragesTotal > 0) {
			// find highest priced Beverages in order,
			List<OrderItem> items = order.getItems().stream().filter(orderItem -> orderItem.getItem().isBeverage())
					.sorted((a, b) -> ((int) (100 * (b.getItem().getPrice() - a.getItem().getPrice()))))
					.collect(Collectors.toList());
			int listitemnr = 0;
			int freebeverages = freebeveragesTotal;
			while (freebeverages > 0 && listitemnr < items.size()) {
				OrderItem orderedProduct = items.get(listitemnr);
				int countOfBeverages = orderedProduct.getCount();// may have ordered several
				for (int i = 0; (i < countOfBeverages && freebeverages > 0); i++) {
					calcOrder.addPriceReduction(orderedProduct.getItem().getPrice(),
							"Free Drink Nr:" + (freebeveragesTotal - freebeverages+1));
					freebeverages--;
				}
				listitemnr++;
			}
		}

		Integer countSnacks = order.getItems().stream().filter(orderItem -> orderItem.getItem().isSnack())
				.mapToInt(orderItem -> orderItem.getCount()).sum();
		int totalCountOfFreeExtras = Math.min(countSnacks, countBeverages);
		if (totalCountOfFreeExtras > 0) {
			int countOfFreeExtras = totalCountOfFreeExtras;
			// find highest priced Extra
			List<ProductExtra> extras = order.getItems().stream()
					.flatMap(orderItem -> Collections.nCopies(orderItem.getCount(), orderItem).stream())
					.flatMap(orderItem -> orderItem.getExtras().stream())
					.sorted((a, b) -> ((int) (100 * (b.getPrice() - a.getPrice())))).collect(Collectors.toList());

			for (int i = 0; (i < countOfFreeExtras && i < extras.size()); i++) {
				calcOrder.addPriceReduction(extras.get(i).getPrice(), "Free Extra Nr:" + (i + 1));
			}

		}
		for (OrderItem item: order.getItems()) {
			total=total+item.getCount() * item.getItem().getPrice();
			for (ProductExtra extra:item.getExtras()) {
				total=total+item.getCount() * extra.getPrice();
		 	}
		}
		for (OrderPriceReduction reduction: calcOrder.getPriceReductions()) {
			totalMinus=totalMinus+reduction.getPriceReduction();
		}
		calcOrder.setTotalCost(total);
		calcOrder.setTotalReduction(totalMinus);
		calcOrder.setTotal(total-totalMinus);
        return calcOrder;
	}

	public String createOrderRezeipt(OrderCalculated orderCalculated) {
		StringBuilder buf = new StringBuilder();
		int posNr=0;
	
		for (OrderItem item: orderCalculated.getOrder().getItems()) {
			posNr++;
			buf.append( String.format("Nr:%d  %d X %50s   %.2f %n", posNr, item.getCount(), item.getItem().getLabel()  , item.getCount() * item.getItem().getPrice()));
			for (ProductExtra extra:item.getExtras()) {
				posNr++;
				buf.append( String.format("Nr:%d  +++ %50s   %.2f %n", posNr,   extra.getLabel()  ,item.getCount() * extra.getPrice()));
			}
		}
		for (OrderPriceReduction reduction: orderCalculated.getPriceReductions()) {
			posNr++;
			
			buf.append( String.format("Nr:%d  *** %50s  -%.2f %n", posNr,  reduction.getReason()  , reduction.getPriceReduction() ));
		}
		buf.append( String.format("Total %54s  %.2f %n","", orderCalculated.getTotal()));
		return buf.toString();
	}
}
