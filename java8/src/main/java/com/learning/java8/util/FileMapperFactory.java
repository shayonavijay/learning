package com.learning.java8.util;

import com.learning.java8.mapper.IFileObjectMapper;
import com.learning.java8.mapper.impl.StatementMapper;

/**
 * 
 * @author vijay
 *
 */
public class FileMapperFactory {
	
	public static  IFileObjectMapper<? extends IModel> getFileObjectMapper(String path) {
	
		
		String fileName = path.substring(path.lastIndexOf("/")+1);
		if(fileName.contains("sample")) {
			return  (IFileObjectMapper<? extends IModel>) new StatementMapper();
		}
		
		return null;
		
	}

}
