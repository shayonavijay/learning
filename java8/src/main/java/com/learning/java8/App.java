package com.learning.java8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.learning.java8.mapper.IFileObjectMapper;
import com.learning.java8.model.Statement;
import com.learning.java8.processor.IFileReportGenerator;
import com.learning.java8.reader.IFileReader;
import com.learning.java8.util.FileMapperFactory;
import com.learning.java8.util.FileReaderFactory;
import com.learning.java8.util.FileReportGeneratorFactory;
import com.learning.java8.util.IModel;

public class App {

	static List<Statement> transactions = new ArrayList<Statement>();
	static Statement transaction = null;
	static String path = "/Users/vijay/Documents/GitHub/learning/java8/src/main/java/com/learning/java8/sample.txt";

	public static void main(String[] args) {
		System.out.println("Hello World!");
		process();
	}

	private static void process() {
		
		IFileReader reader = FileReaderFactory.getFileReader(path);
		
		IFileObjectMapper<? extends IModel> mapper =  FileMapperFactory.getFileObjectMapper(path);
		
		IFileReportGenerator processor = FileReportGeneratorFactory.getReportGenerator(path);
		
		try {
			List<String> records = reader.read();
			
			List<? extends IModel> listOfModel = mapper.mappedToObjects(records);
			
			System.out.println("statements count " + listOfModel.size());
			
			processor.process(listOfModel);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}