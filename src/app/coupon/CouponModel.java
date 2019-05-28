package app.coupon;

public class CouponModel {

	private String id;
	private String shopId;
	private String shopName;
	private String product;

	private double discountedPrice;
	private double originalPrice;
	private double sale;

	public CouponModel(String shop, String product, double dPrice, double oPrice) {
		this.shopId = shop;
		this.product = product;
		this.discountedPrice = dPrice;
		this.originalPrice = oPrice;
	}

	public CouponModel(Coupon coupon) {
		this.id = coupon.getId();
		this.shopId = coupon.getShop().getId();
		this.shopName = coupon.getShop().getName();
		this.product = coupon.getProduct();
		this.discountedPrice = coupon.getDiscountedPrice();
		this.originalPrice = coupon.getOriginalPrice();
		this.sale = (1.0 - discountedPrice / originalPrice) * 100;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}
}
