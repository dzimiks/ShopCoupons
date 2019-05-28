package app;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest")
public class Rest extends ResourceConfig {

	public Rest() {
		packages("app.coupon", "app.shop");
	}
}
