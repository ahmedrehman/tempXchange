package ch.ahmed.tests.coffeeshoporders.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.ahmed.tests.coffeeshoporders.data.Order;
import ch.ahmed.tests.coffeeshoporders.data.OrderCalculated;
import ch.ahmed.tests.coffeeshoporders.data.Product;
import ch.ahmed.tests.coffeeshoporders.data.ProductCategory;
import ch.ahmed.tests.coffeeshoporders.data.ProductExtra;
import ch.ahmed.tests.coffeeshoporders.data.ProductVariant;
import ch.ahmed.tests.coffeeshoporders.util.ShopUtils;

public class OrderManagerTest {
	@Before
	public void setupShopData() {
		Shop.getInstance().initProducts(ShopUtils.loadDefaultProducts());
	}
	
	@Test
	public void testOrders() {
		
		 Shop shop=Shop.getInstance();
		 
		 Order order= Order.createOrder(102);
		 
		 ProductVariant productVariant= shop.findProductVariantByName("COFFEE_LARGE");
		 ProductExtra extra=productVariant.findProductExtraByName("EXTRA_MILK");
		 order.addOrderItem(productVariant, Arrays.asList(extra), 1);
		 ProductVariant productVariant2= shop.findProductVariantByName("COFFEE_SMALL");
		 ProductExtra extra2=productVariant2.findProductExtraByName("SPEACIAL_ROAST_COFFEE");
		 order.addOrderItem(productVariant2, Arrays.asList(extra2), 1);
		 ProductVariant productVariant3= shop.findProductVariantByName("BACON_ROLL");
		 order.addOrderItem(productVariant3, null, 1);
		 ProductVariant productVariant5= shop.findProductVariantByName("BACON_ROLL");
		 order.addOrderItem(productVariant5, null, 1);
		 ProductVariant productVariant4= shop.findProductVariantByName("ORANGEJUICE_FRESH");
		 order.addOrderItem(productVariant4, null, 1);
		 
		 
		 OrderCalculated orderCalculated= shop.calculateOrder(order, 1);
		 
		 String receipt = shop.createOrderRezeipt(orderCalculated);
		 System.out.println(receipt);

	}

}
