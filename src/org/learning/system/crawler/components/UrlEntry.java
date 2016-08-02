package org.learning.system.crawler.components;

public class UrlEntry {
	public String url;
	public Integer priority;
	public UrlEntry(String u, int p) {
		url = u;
		priority = p;
	}
}
