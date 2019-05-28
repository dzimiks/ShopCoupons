package app.coupon;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/coupons")
public class CouponController {

	private final CouponService couponService;

	public CouponController() {
		this.couponService = new CouponService();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CouponModel> getCoupons() {
		return couponService.getCoupons();
	}

	@DELETE
	@Path("/{id}")
	public boolean deleteCoupon(@PathParam("id") String id) {
		return couponService.deleteCoupon(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public CouponModel addCoupon(@FormParam("shopName") String shop, @FormParam("product") String product, @FormParam("discountedPrice") double discountedPrice, @FormParam("originalPrice") double originalPrice) {
		return couponService.addCoupon(new CouponModel(shop, product, discountedPrice, originalPrice));
	}
}
