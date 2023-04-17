package ch.ahmed.tests.coffeeshoporders.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.ahmed.tests.coffeeshoporders.data.Order;
import ch.ahmed.tests.coffeeshoporders.data.OrderCalculated;
import ch.ahmed.tests.coffeeshoporders.data.Product;
import ch.ahmed.tests.coffeeshoporders.data.ProductCategory;
import ch.ahmed.tests.coffeeshoporders.data.ProductExtra;
import ch.ahmed.tests.coffeeshoporders.data.ProductVariant;
import ch.ahmed.tests.coffeeshoporders.service.Shop;

public class ShopUtils {
	public static enum COFFEE_SIZE {
		COFFEE_SMALL, COFFEE_MEDIUM, COFFEE_LARGE
	}

	public static enum COFFEE_EXTRA {
		EXTRA_MILK, FOAMED_MILK, SPEACIAL_ROAST_COFFEE
	}

	public static List<Product> createSampleProductData(boolean overwriteDefaultStartupDataFile) {
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

		if (overwriteDefaultStartupDataFile) {
			Utils.serialiseToFile(products, "testData.ser");
		}

		return products;
	}

	public static List<Product> loadDefaultProducts() {
		List<Product> products = (List<Product>) Utils.deserialiseObjectFromFile("testData.ser");
		return products;
	}

	public static Order createSampleOrder() {

		Shop shop = Shop.getInstance();
		Order order = Order.createOrder(102);

		addOrder4CoffeeWithMilk(shop, order);
		addOrder4CoffeeSmallWithRoast(shop, order);
		addOrder4Bacon(shop, order);
		addOrder4OrangeJuice(shop, order);

		return order;
	}

	public static void addOrder4OrangeJuice(Shop shop, Order order) {
		ProductVariant productVariant4 = shop.findProductVariantByName("ORANGEJUICE_FRESH");
		order.addOrderItem(productVariant4, null, 1);
	}

	public static void addOrder4Bacon(Shop shop, Order order) {
		ProductVariant productVariant3 = shop.findProductVariantByName("BACON_ROLL");
		order.addOrderItem(productVariant3, null, 1);
	}

	public static void addOrder4CoffeeSmallWithRoast(Shop shop, Order order) {
		ProductVariant productVariant2 = shop.findProductVariantByName("COFFEE_SMALL");
		ProductExtra extra2 = productVariant2.findProductExtraByName("SPEACIAL_ROAST_COFFEE");
		order.addOrderItem(productVariant2, Arrays.asList(extra2), 1);
	}

	public static void addOrder4CoffeeWithMilk(Shop shop, Order order) {
		ProductVariant productVariant = shop.findProductVariantByName("COFFEE_LARGE");
		ProductExtra extra = productVariant.findProductExtraByName("EXTRA_MILK");
		order.addOrderItem(productVariant, Arrays.asList(extra), 1);
	}

	public static void addOrder4Coffee(Shop shop, Order order, COFFEE_SIZE size, COFFEE_EXTRA extra1, int count) {
		ProductVariant productVariant = shop.findProductVariantByName(size.name());
		ProductExtra extra = productVariant.findProductExtraByName(extra1.name());
		order.addOrderItem(productVariant, Arrays.asList(extra), count);
	}
}
