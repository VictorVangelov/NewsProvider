package model;

import java.util.HashMap;
import java.util.Map;

public class RssContainer {
	public static final Map<String, String> categoryLinkContainer;
	static {
		categoryLinkContainer = new HashMap<String, String>();
		categoryLinkContainer.put("busines", "http://www.dnevnik.bg/rssc/?rubrid=1661");
		categoryLinkContainer.put("companies", "http://www.dnevnik.bg/rssc/?rubrid=1665");
		categoryLinkContainer.put("finansies", "http://www.dnevnik.bg/rssc/?rubrid=1666");
		categoryLinkContainer.put("tourism", "http://www.dnevnik.bg/rssc/?rubrid=1668");
		
		categoryLinkContainer.put("europe", "http://www.dnevnik.bg/rssc/?rubrid=1674");
		categoryLinkContainer.put("euronews", "http://www.dnevnik.bg/rssc/?rubrid=1675");
		categoryLinkContainer.put("education", "http://www.dnevnik.bg/rssc/?rubrid=1676");
		categoryLinkContainer.put("work", "http://www.dnevnik.bg/rssc/?rubrid=1677");
		categoryLinkContainer.put("sport", "http://www.dnevnik.bg/rssc/?rubrid=1692");
		categoryLinkContainer.put("bgfootball", "http://www.dnevnik.bg/rssc/?rubrid=1693");
		categoryLinkContainer.put("worldfootball", "http://www.dnevnik.bg/rssc/?rubrid=1694");
		categoryLinkContainer.put("auto-moto", "http://www.dnevnik.bg/rssc/?rubrid=1695");
		categoryLinkContainer.put("tenis", "http://www.dnevnik.bg/rssc/?rubrid=1696");
		categoryLinkContainer.put("volleyball", "http://www.dnevnik.bg/rssc/?rubrid=2100");
		
		categoryLinkContainer.put("technology", "http://www.dnevnik.bg/rssc/?rubrid=1660");
		categoryLinkContainer.put("financing", "http://www.dnevnik.bg/rssc/?rubrid=1298");
		categoryLinkContainer.put("eurofonds", "http://www.dnevnik.bg/rssc/?rubrid=303");
		
	}

}
