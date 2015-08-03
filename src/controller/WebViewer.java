package controller;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;

@Path("/news")
@Produces(MediaType.TEXT_HTML)
public class WebViewer {

	@GET
	@Path("/{category}")
	@Produces(MediaType.TEXT_HTML)
	public String getHTMLResponse(@PathParam("category") String category) throws JSONException {

		return String.format(NewsServices.siteTemplate, NewsServices.getNewsHtmlForm(category));
	}
	

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getWelcomePage() {

		return String.format(NewsServices.siteTemplate, NewsServices.contentInMain);

	}

}