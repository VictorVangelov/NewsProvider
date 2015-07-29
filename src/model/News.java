package model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class News {
	private String title;
	private String link;
	private String description;
	
	public News() {}
	
	public News(String title, String link, String description){
		this.title = title;
		this.link = link;
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public String getDescription() {
		return description;
	}

}
