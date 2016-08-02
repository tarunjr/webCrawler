package org.learning.system.crawler.simple.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.learning.system.crawler.components.HttpFetcher;

public class HttpFetcherService implements Service {
	
	private final HttpFetcher fetcher;
	private final ExecutorService exec;
	private final MessageChannel<String> input;
	private final MessageChannel<String> output;
	private final int numThreads;
	private List<Runnable> tasks;
	
	public HttpFetcherService(HttpFetcher fetcher, 
							MessageChannel<String> input, 
							MessageChannel<String> output, 
							ExecutorService exec, 
							int numThreads) {
		this.fetcher = fetcher;
		this.input = input;
		this.output = output;
		this.exec = exec;
		this.numThreads = numThreads;
	}
	
	@Override
	public void start() {
		List<Runnable> tasks = new ArrayList<Runnable>();
		for(int i=0; i < numThreads;i++) {
			Runnable task = new FetcherTask(fetcher, input, output);
			tasks.add(task);
			exec.submit(task);
		}
	}

	@Override
	public void stop() {
		this.exec.shutdown();
		try {
			this.exec.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private class FetcherTask implements Runnable {
		private final HttpFetcher fetcher;
		private final MessageChannel<String> input;
		private final MessageChannel<String> output;
		
		public FetcherTask(HttpFetcher fetcher, 
					MessageChannel<String> input, 
					MessageChannel<String> output) {
			this.fetcher = fetcher;
			this.input = input;
			this.output = output;
		}
		@Override
		public void run() {
			while (true) {
				String url = input.get();
				try {
					String doc=fetcher.fetch(url);
					output.put(doc);
				} catch (RejectedExecutionException ex) {
					
				}
			}
		}
	}
}
