package app.coupon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CouponRepository {

	private static String[] SHOP_NAME_LIST = {
			"BOSCH",
			"Win Win",
			"Maxi",
			"MC Donalds",
			"Idea",
			"KFC",
			"Tepsija",
			"RAF",
			"Brankow"
	};
	private static List<Shop> SHOP_LIST;
	private static List<Coupon> COUPON_LIST = new ArrayList<>();

	static {
		SHOP_LIST = generateShops();
		COUPON_LIST.add(new Coupon(SHOP_LIST.get(0), "BOSCH masina", 12439.43, 10322.11));
		COUPON_LIST.add(new Coupon(SHOP_LIST.get(1), "Hama Mouse", 4224, 2999));
		COUPON_LIST.add(new Coupon(SHOP_LIST.get(2), "Paprika", 85, 54));
		COUPON_LIST.add(new Coupon(SHOP_LIST.get(3), "Double cheeseburger", 450, 399));
	}

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

	public synchronized static List<Coupon> getCoupons() {
		return COUPON_LIST;
	}

	public synchronized static Coupon addCoupon(CouponModel cModel) {
		UUID uuid = UUID.randomUUID();
		Shop shop = null;

		for (Shop s : SHOP_LIST) {
			if (s.getId().equals(cModel.getShopId())) {
				shop = s;
			}
		}

		if (shop != null) {
			Coupon coupon = new Coupon(shop, cModel.getProduct(), cModel.getOriginalPrice(), cModel.getDiscountedPrice());
			coupon.setId(uuid.toString());
			COUPON_LIST.add(coupon);

			return coupon;
		} else {
			return null;
		}
	}

	public synchronized static boolean removeCoupon(String id) {
		for (int i = 0; i < COUPON_LIST.size(); i++) {
			if (COUPON_LIST.get(i).getId().equals(id)) {
				COUPON_LIST.remove(i);
				return true;
			}
		}

		return false;
	}


	public synchronized static boolean addCoupon() {
		COUPON_LIST.add(new Coupon(SHOP_LIST.get(2), "Mleko", 139.99, 119.99));
		return true;
	}
}
