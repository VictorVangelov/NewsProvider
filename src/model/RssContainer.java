package model;

import java.util.HashMap;
import java.util.Map;

public class RssContainer {
	public static final Map<String, String> categoryLinkContainer;
	static {
		categoryLinkContainer = new HashMap<String, String>();
		categoryLinkContainer.put("bulgaria", "http://www.dnevnik.bg/rssc/?rubrid=1657");
		categoryLinkContainer.put("technology", "http://www.dnevnik.bg/rssc/?rubrid=1660");
		categoryLinkContainer.put("world", "http://www.dnevnik.bg/rssc/?rubrid=1658");
		categoryLinkContainer.put("sport", "http://www.dnevnik.bg/rssc/?rubrid=1692");
		categoryLinkContainer.put("business", "http://www.dnevnik.bg/rssc/?rubrid=1661");	
	}

}
