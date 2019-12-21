package com.learning.java8.mapper.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.learning.java8.mapper.IFileObjectMapper;
import com.learning.java8.model.Statement;
import com.learning.java8.util.IModel;

/**
 * 
 * @author vijay
 *
 */
public class StatementMapper extends  IFileObjectMapper<IModel>{


	@Override
	public List<? extends IModel> mappedToObjects(List<String> contents) {
		Objects.requireNonNull(contents);
		return contents.stream().map(mapper()).collect(Collectors.toList());
	}
	
	private Function<String,Statement> mapper() {
		
		return (line) -> {
			String[] record = line.split(",");
			try {
				LocalDate date = LocalDate.parse(record[0]);
				BigDecimal amount = new BigDecimal(record[1]);
				String description = record[2];
				return new Statement(date, amount, description) ;
			} catch (Exception e) {
				throw new ArrayIndexOutOfBoundsException("Not valide Recrods "+record);
			}
			
	};
	}

	
	
	

}
