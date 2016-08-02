package org.learning.system.crawler.simple.service;

public interface MessageChannel<T> {
	public void put(T data);
	public T get();
}
