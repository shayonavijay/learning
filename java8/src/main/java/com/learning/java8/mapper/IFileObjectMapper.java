package com.learning.java8.mapper;

import java.util.List;

/**
 * 
 * @author vijay
 *
 */
public abstract class IFileObjectMapper<T> {
	
	public abstract List<? extends T> mappedToObjects(List<String> contents);

}
