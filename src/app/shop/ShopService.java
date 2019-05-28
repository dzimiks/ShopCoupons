package app.shop;

import java.util.List;

public class ShopService {

	public List<Shop> getShops() {
		return ShopRepository.getShops();
	}
}
