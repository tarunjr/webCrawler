package org.learning.system.crawler.simple.components;

import java.util.concurrent.PriorityBlockingQueue;

import org.learning.system.crawler.components.Frontier;
import org.learning.system.crawler.components.UrlEntry;

public class SimpleFrontier implements Frontier {
    private PriorityBlockingQueue<UrlEntry> urlQueue;
	
    public SimpleFrontier(PriorityBlockingQueue<UrlEntry> queue) {
    	this.urlQueue = queue;
    }
    @Override
	public String getNext() {
		UrlEntry ue = urlQueue.poll();
		return (ue!= null) ? ue.url : null;
	}
	@Override
	public void add(String url, int priority) {
		urlQueue.add(new UrlEntry(url, priority));
		return;
	}
}
