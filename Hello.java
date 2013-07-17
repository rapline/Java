package test.alc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.view.Viewable;

@Path("/hello/{name}")
public class Hello {

	@PathParam("name")
	private String name;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Viewable sayHello() {

		return new Viewable("/hello.jsp", this.name);
	}
}