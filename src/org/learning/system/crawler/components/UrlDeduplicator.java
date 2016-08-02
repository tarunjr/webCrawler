package org.learning.system.crawler.components;

import java.util.List;

public interface UrlDeduplicator {
	public List<String> dedup(List<String> urls);
}
