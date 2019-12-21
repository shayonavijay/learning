package com.learning.java8.util;

import com.learning.java8.processor.IFileReportGenerator;
import com.learning.java8.processor.impl.SummuryReportFileProcessor;

public class FileReportGeneratorFactory {
	
	public static IFileReportGenerator getReportGenerator(String path) {

		String fileName = path.substring(path.lastIndexOf("/")+1);

		if (fileName.startsWith("sample")) {
			return new SummuryReportFileProcessor();
		}
		return null;
	}

}
