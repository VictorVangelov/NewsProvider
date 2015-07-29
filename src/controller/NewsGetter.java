package controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.News;
import model.RssContainer;

import org.codehaus.jettison.json.JSONException;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Path("/rest/news")
public class NewsGetter {

	public static List<News> getNews(String categoryLink)
			throws JSONException {
		ArrayList<SyndEntry> syndEntrys = getSyndEntrys(categoryLink);
		String link, description, title;
		List<News> listNews = new ArrayList<News>();
		for (SyndEntry entry : syndEntrys) {
			title = entry.getTitle();
			link = entry.getLink();
			description = entry.getDescription().getValue();
			listNews.add(new News(title, link, description));
		}
		return listNews;

	}

	public static ArrayList<SyndEntry> getSyndEntrys(String link) {
		ArrayList<SyndEntry> listOfSyndEntrys = new ArrayList<SyndEntry>();
		try {
			URL url = new URL(link);
			HttpURLConnection httpcon = (HttpURLConnection) url
					.openConnection();
			// Reading the feed
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(httpcon));
			List<SyndEntry> entries = feed.getEntries();
			Iterator<SyndEntry> itEntries = entries.iterator();
			SyndEntry entry;
			StringBuilder siteContent = new StringBuilder();
			while (itEntries.hasNext()) {
				entry = itEntries.next();

				listOfSyndEntrys.add(entry);
			}
			return listOfSyndEntrys;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listOfSyndEntrys;

	}

	@POST
	@Path("/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> JSONResponse(@PathParam("category") String category)
			throws JSONException {
		String categoryLink = RssContainer.categoryLinkContainer.get(category
				.toLowerCase());
		return NewsGetter.getNews(categoryLink);
	}

	protected List<News> serializeToObjects(org.json.JSONArray restResponse) {
		org.json.JSONArray jNewsArr = restResponse;
		org.json.JSONObject tempObject;
		int size = jNewsArr.length();
		List<News> newsList = new ArrayList<News>();
		String title, link, description;
		for (int i = 0; i < size; i++) {
			tempObject = jNewsArr.getJSONObject(i);
			title = tempObject.getString("title");
			link = tempObject.getString("link");
			description = tempObject.getString("description");
			newsList.add(new News(title, link, description));
		}
		return newsList;
	}

	protected org.json.JSONArray getRestResponse(String category)
			throws UnirestException {

		return Unirest
				.post("http://localhost:8080/RssFeeder/rest/news/" + category)
				.asJson().getBody().getArray();

	}

}
