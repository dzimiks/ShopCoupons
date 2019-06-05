package app.coupon;

import app.shop.Shop;
import app.shop.ShopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CouponRepository {

	private static List<Coupon> COUPON_LIST = new ArrayList<>();
	private static List<Shop> SHOP_LIST = ShopRepository.getShops();

	static {
		COUPON_LIST.add(new Coupon(SHOP_LIST.get(0), "BOSCH masina", 12439.43, 10322.11));
		COUPON_LIST.add(new Coupon(SHOP_LIST.get(1), "Hama Mouse", 4224.43, 2999.99));
		COUPON_LIST.add(new Coupon(SHOP_LIST.get(2), "Paprika", 85.99, 54.99));
		COUPON_LIST.add(new Coupon(SHOP_LIST.get(3), "Double cheeseburger", 499.99, 399.99));
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
}
