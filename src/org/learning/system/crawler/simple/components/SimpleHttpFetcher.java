package org.learning.system.crawler.simple.components;

import org.learning.system.crawler.components.HttpFetcher;

public class SimpleHttpFetcher implements HttpFetcher {

	@Override
	public String fetch(String url) {
		return url;
		
	}

}
