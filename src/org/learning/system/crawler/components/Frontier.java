package org.learning.system.crawler.components;

public interface Frontier {
	public String getNext();
	public void add(String url, int priority);
}
