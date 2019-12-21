package com.learning.java8.reader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * 
 * @author vijay
 *
 */
public interface IFileReader {
	
	List<String> read( ) throws IOException ;

}
