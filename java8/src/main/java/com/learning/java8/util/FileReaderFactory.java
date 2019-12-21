package com.learning.java8.util;

import java.util.Objects;

import com.learning.java8.reader.IFileReader;
import com.learning.java8.reader.impl.TextFileReader;

/**
 * 
 * @author vijay
 *
 */
public class FileReaderFactory {

	public static IFileReader getFileReader(String path) {
		Objects.requireNonNull(path);

		String fileName = path.substring(path.lastIndexOf("/")+1);

		if (fileName.startsWith("sample")) {
			return new TextFileReader(path);
		}
		return null;
	}

}
