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

public class OrderManagerTest {
	@Before
	public void setupShopData() {
		List<Product> products = new ArrayList();
		List<ProductVariant> variants = new ArrayList();
		List<ProductExtra> extras = new ArrayList();

		// Coffee
		extras.add(new ProductExtra("EXTRA_MILK", "Extra milk", 0.30d));
		extras.add(new ProductExtra("FOAMED_MILK", "Foamed milk", 0.50d));
		extras.add(new ProductExtra("SPEACIAL_ROAST_COFFEE", "Special roast coffee", 0.90d));
		
		variants.add(new ProductVariant("COFFEE_SMALL", ProductCategory.BEWERAGE, "Coffee (small)", 2.50d, extras));
		variants.add(new ProductVariant("COFFEE_MEDIUM", ProductCategory.BEWERAGE, "Coffee (medium)", 3.00d, extras));
		variants.add(new ProductVariant("COFFEE_LARGE", ProductCategory.BEWERAGE, "Coffee (large)", 3.50d, extras));
		
		products.add(new Product("COFFEE", "Coffee", variants));

		// orange

		variants = new ArrayList();
		extras = new ArrayList();
		variants.add(new ProductVariant("ORANGEJUICE_FRESH", ProductCategory.BEWERAGE,
				"Freshly squeezed orange juice (0.25l)", 3.95d, extras));
		products.add(new Product("ORANGEJUICE", "Orange Juice", variants));

		// Bacon

		variants = new ArrayList();
		extras = new ArrayList();
		variants.add(new ProductVariant("BACON_ROLL", ProductCategory.SNACK, "Bacon Roll", 4.50d, extras));
		products.add(new Product("BACON_ROLL", "Bacon Roll", variants));
        
		// init Shop
		Shop.getInstance().initProducts(products);
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
