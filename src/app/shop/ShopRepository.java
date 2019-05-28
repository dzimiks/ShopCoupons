package app.shop;

import java.util.ArrayList;
import java.util.List;

public class ShopRepository {

	private static String[] SHOP_NAME_LIST = {
			"BOSCH",
			"Win Win",
			"Maxi",
			"MC Donalds",
			"Idea",
			"KFC",
			"Tepsija",
			"RAF",
			"Brankow",
			"Coffee Dream",
			"Hotel Moskva"
	};
	private static List<Shop> SHOP_LIST = generateShops();

	private static List<Shop> generateShops() {
		List<Shop> shops = new ArrayList<>();

		for (int i = 0; i < SHOP_NAME_LIST.length; i++) {
			Shop shop = new Shop();
			shop.setId(Integer.toString(i));
			shop.setName(SHOP_NAME_LIST[i]);
			shops.add(shop);
		}

		return shops;
	}

	public synchronized static List<Shop> getShops() {
		return SHOP_LIST;
	}
}
