package org.learning.system.crawler.simple.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class StringBlockingQueueChannel implements MessageChannel<String> {

	private final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	
	@Override
	public void put(String data) {
		queue.add(data);
	}

	@Override
	public String get() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
