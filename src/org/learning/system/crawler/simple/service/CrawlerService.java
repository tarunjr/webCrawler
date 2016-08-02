package org.learning.system.crawler.simple.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import org.learning.system.crawler.components.Frontier;
import org.learning.system.crawler.components.HttpFetcher;
import org.learning.system.crawler.components.LinkExtractor;
import org.learning.system.crawler.components.UrlEntry;
import org.learning.system.crawler.simple.components.SimpleFrontier;
import org.learning.system.crawler.simple.components.SimpleHttpFetcher;
import org.learning.system.crawler.simple.components.SimpleLinkExtractor;

public class CrawlerService implements Service {

	@Override
	public void start() {
		MessageChannel<String> pageFetched = new StringBlockingQueueChannel();
		MessageChannel<String> linkDiscovered = new StringBlockingQueueChannel();
		MessageChannel<String> urlDiscovered = new StringBlockingQueueChannel();
		
		HttpFetcher fetcher = new SimpleHttpFetcher();
		ExecutorService exec1 = Executors.newFixedThreadPool(4);
		HttpFetcherService sv1 = new HttpFetcherService(fetcher, urlDiscovered, pageFetched, exec1, 4);
		sv1.start();
		
		LinkExtractor extractor = new SimpleLinkExtractor();
		ExecutorService exec2 = Executors.newFixedThreadPool(4);
		LinkExtractorService sv2 = new LinkExtractorService(extractor, pageFetched, linkDiscovered, exec2);
		sv2.start();
		
		PriorityBlockingQueue<UrlEntry> queue = new PriorityBlockingQueue<UrlEntry>();
		fillSeedFrontier(queue);
		Frontier frontier = new SimpleFrontier(queue);
		ExecutorService exec3 = Executors.newFixedThreadPool(1);
		FrontierService sv3 = new FrontierService(frontier, linkDiscovered, urlDiscovered, exec3);
		sv3.start();
	}

	@Override
	public void stop() {
	
		
	}
	private void fillSeedFrontier(PriorityBlockingQueue<UrlEntry> queue) {
		queue.add(new UrlEntry("www.news.google.com", 0));
		queue.add(new UrlEntry("www.yahoo.com", 0));
	}

}
