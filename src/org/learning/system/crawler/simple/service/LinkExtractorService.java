package org.learning.system.crawler.simple.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

import org.learning.system.crawler.components.LinkExtractor;

public class LinkExtractorService implements Service {
	private final MessageChannel<String> input;
	private final MessageChannel<String> output;
	private final LinkExtractor extractor;
	private ExecutorService exec;
	
	public LinkExtractorService(LinkExtractor extractor, MessageChannel<String> input, MessageChannel<String> output, ExecutorService exec) {
		this.input = input;
		this.output = output;
		this.extractor = extractor;
		this.exec = exec;
	}
	@Override
	public void start() {
		while (!exec.isShutdown()) {
			String doc = input.get();
			try {
				exec.submit(new Runnable() {
							public void run() { 
								List<String> links = extractor.extract(doc); 
								for(String link: links)
									output.put(link);
							}
						});
			} catch (RejectedExecutionException ex) {
				if (!exec.isShutdown())
					System.out.println(ex.getMessage());
			}
		}
	}

	@Override
	public void stop() {
		this.exec.shutdown();
	}

}
