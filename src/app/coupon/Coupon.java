package app.coupon;

import app.shop.Shop;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Coupon implements Serializable {

	private String id;
	private Shop shop;
	private String product;
	private double discountedPrice;
	private double originalPrice;

	public Coupon(Shop shop, String product, double originalPrice, double discountedPrice) {
		UUID uuid = UUID.randomUUID();
		this.id = uuid.toString();
		this.shop = shop;
		this.product = product;
		this.discountedPrice = discountedPrice;
		this.originalPrice = originalPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Shop getShop() {
		return shop;
	}

	public String getProduct() {
		return product;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, shop, product, discountedPrice, originalPrice);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coupon coupon = (Coupon) o;
		return Double.compare(coupon.discountedPrice, discountedPrice) == 0 &&
				Double.compare(coupon.originalPrice, originalPrice) == 0 &&
				Objects.equals(id, coupon.id) &&
				Objects.equals(shop, coupon.shop) &&
				Objects.equals(product, coupon.product);
	}
}
