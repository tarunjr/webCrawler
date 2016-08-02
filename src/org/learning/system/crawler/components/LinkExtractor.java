package org.learning.system.crawler.components;

import java.util.List;

public interface LinkExtractor {
	public List<String> extract(String doc);
}
