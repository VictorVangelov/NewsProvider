package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.News;
import model.RssContainer;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Path("/rest/news")
@Produces(MediaType.APPLICATION_JSON)
public class NewsServices {
	protected static NewsServices templateGetter = new NewsServices();
	protected static String siteTemplate = templateGetter
			.getTemplate("index.html");
	protected static String contentInMain = templateGetter
			.getTemplate("mainHome.html");
	protected static String templateNews = templateGetter
			.getTemplate("templateNews.html");

	@GET
	@Path("/{category}")
	@Produces(value = {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	public List<News> JSONResponse(@PathParam("category") String category) {
		String categoryLink = RssContainer.categoryLinkContainer.get(category
				.toLowerCase());
		return NewsServices.getNews(categoryLink);
	}

	static String getNewsHtmlForm(String category) {

		String categoryLink = RssContainer.categoryLinkContainer.get(category
				.toLowerCase());
		StringBuilder sb = new StringBuilder();

		for (News singleNews : NewsServices.getNews(categoryLink)) {
			sb.append(String.format(templateNews, singleNews.getLink(),
					singleNews.getTitle(), singleNews.getDescription()));
		}
		return sb.toString();
	}

	public static List<News> getNews(String categoryLink) {
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

	@SuppressWarnings("unchecked")
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

	public String getTemplate(String templateName) {
		InputStream is = getClass().getClassLoader().getResourceAsStream(
				templateName);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String message;
		try {
			message = org.apache.commons.io.IOUtils.toString(br);
		} catch (IOException e) {
			message = "no such file";
		}

		return message;

	}

}
