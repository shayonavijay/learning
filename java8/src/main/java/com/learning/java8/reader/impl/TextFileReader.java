package com.learning.java8.reader.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.learning.java8.reader.IFileReader;

/**
 * 
 * @author vijay
 *
 */
public class TextFileReader implements IFileReader {

	private String path;
	
	public TextFileReader(String path) {
		super();
		this.path = path;
	}

	@Override
	public List<String> read() throws IOException {
		Path filePath = Paths.get(path);
		return Files.lines(filePath).collect(Collectors.toList());
	}

}
