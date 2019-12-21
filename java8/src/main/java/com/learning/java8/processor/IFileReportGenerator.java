package com.learning.java8.processor;

import java.util.List;

import com.learning.java8.util.IModel;

public interface IFileReportGenerator {
	
	void process(List<? extends IModel> listOfModel);

}
