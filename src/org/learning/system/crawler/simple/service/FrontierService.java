package org.learning.system.crawler.simple.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

import org.learning.system.crawler.components.Frontier;
import org.learning.system.crawler.components.HttpFetcher;

public class FrontierService  implements Service {
	
	private final Frontier frontier;
	private ExecutorService exec;
	private final MessageChannel<String> input;
	private final MessageChannel<String> output;
	
	public FrontierService(Frontier frontier, 
							MessageChannel<String> input, 
							MessageChannel<String> output, 
							ExecutorService exec) {
		this.frontier = frontier;
		this.input = input;
		this.output = output;
		this.exec = exec;
	}
	
	@Override
	public void start() {
		while(true) {
			String url = frontier.getNext();
			if(url == null)
				break;
			input.put(url);
		}
		
		exec.submit(new FrontierTask(frontier, input, output));
	}
	@Override
	public void stop() {
		this.exec.shutdown();
	}
	
	private class FrontierTask implements Runnable {
		private final Frontier frontier;
		private final MessageChannel<String> input;
		private final MessageChannel<String> output;
		
		public FrontierTask(Frontier frontier, 
							MessageChannel<String> input, 
							MessageChannel<String> output) {
			this.frontier = frontier;
			this.input = input;
			this.output = output;
		}
		@Override
		public void run() { 
			while(true) {
				String url = input.get();
			}
		}
	}
}
