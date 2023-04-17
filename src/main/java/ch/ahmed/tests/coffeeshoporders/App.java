package ch.ahmed.tests.coffeeshoporders;

import java.util.List;

import ch.ahmed.tests.coffeeshoporders.data.Order;
import ch.ahmed.tests.coffeeshoporders.data.OrderCalculated;
import ch.ahmed.tests.coffeeshoporders.data.Product;
import ch.ahmed.tests.coffeeshoporders.service.Shop;
import ch.ahmed.tests.coffeeshoporders.util.ShopUtils;
import ch.ahmed.tests.coffeeshoporders.util.Utils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Coffee World!" );

        // ShopUtils.createSampleProductData(true); // create the data file
        System.out.println( "Initialising Shop from File Data" );
        Shop shop = Shop.getInstance();
        shop.initProducts(ShopUtils.loadDefaultProducts());
        System.out.println( "Shop ready" );
        System.out.println( "creating sample order" );
        Order order=ShopUtils.createSampleOrder();
        OrderCalculated orderCalculated = shop.calculateOrder(order, 1);
        System.out.println( "printing order" );
		String receipt = shop.createOrderRezeipt(orderCalculated);
		System.out.println(receipt);
		
    }
}
