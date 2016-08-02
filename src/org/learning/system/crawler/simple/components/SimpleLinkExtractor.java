package org.learning.system.crawler.simple.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.learning.system.crawler.components.LinkExtractor;

public class SimpleLinkExtractor implements  LinkExtractor {
	Map<String, List<String>> urlMap = new HashMap<String, List<String>>();
	
	public SimpleLinkExtractor() {
		List<String> li = new ArrayList<String>();
		li.add("www.abc1.com");
		li.add("www.abc2.com");
		li.add("www.abc3.com");
		li.add("www.abc4.com");
		
		
		List<String> l2 = new ArrayList<String>();
		l2.add("www.xyz1.com");
		l2.add("www.xyz2.com");
		l2.add("www.xyz3.com");
		l2.add("www.xyz4.com");
		
		urlMap.put("www.news.google.com", li);
		
		urlMap.put("www.yahoo.com", l2);
	}
	@Override
	public List<String> extract(String doc) {
		// TODO Auto-generated method stub
		return urlMap.get(doc);
	}

}
