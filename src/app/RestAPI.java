package app;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class RestAPI extends ResourceConfig {

	public RestAPI() {
		packages("app.coupon", "app.shop");
	}
}
