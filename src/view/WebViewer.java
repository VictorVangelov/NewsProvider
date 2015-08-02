package view;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.News;
import model.RssContainer;

import org.codehaus.jettison.json.JSONException;

import controller.NewsGetter;

@Path("/news")
public class WebViewer {

	@GET
	@Path("/{category}")
	@Produces(MediaType.TEXT_HTML)
	public String getHTMLResponse(@PathParam("category") String category) {
		String categoryLink = RssContainer.categoryLinkContainer.get(category
				.toLowerCase());
		// String categoryLink = "http://www.dnevnik.bg/rssc/?rubrid=1657";
		StringBuilder sb = new StringBuilder();
		String template = "<item>  <br><a href=%s>%s</a>  <br><br>  <description>%s</description></item><br><br><br><br>";

		try {
			for (News singleNews : NewsGetter.getNews(categoryLink)) {
				sb.append(String.format(template,singleNews.getLink(), singleNews.getTitle(),
						 singleNews.getDescription()));
			}
		} catch (JSONException e) {
			sb.append("<item>  <br><a Cannot generate news</a> </item>");
		}
		return sb.toString();
	}

}