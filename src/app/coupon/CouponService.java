package app.coupon;

import java.util.ArrayList;
import java.util.List;

public class CouponService {

	public List<CouponModel> getCoupons() {
		List<CouponModel> models = new ArrayList<>();
		List<Coupon> coupons = CouponRepository.getCoupons();

		for (Coupon c : coupons) {
			models.add(new CouponModel(c));
		}

		return models;
	}

	public boolean deleteCoupon(String id) {
		return CouponRepository.removeCoupon(id);
	}

	public CouponModel addCoupon(CouponModel cModel) {
		Coupon coupon = CouponRepository.addCoupon(cModel);

		if (coupon != null) {
			return new CouponModel(coupon);
		} else {
			return null;
		}
	}
}
