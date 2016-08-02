package org.learning.system.crawler.simple;

import org.learning.system.crawler.simple.service.CrawlerService;

public class SimpleCrawler {
	
	public static void main(String[] args) {
		CrawlerService svc = new CrawlerService();
		svc.start();
	}
}
