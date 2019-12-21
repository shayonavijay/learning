package com.learning.java8.processor.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.learning.java8.model.Statement;
import com.learning.java8.processor.IFileReportGenerator;
import com.learning.java8.util.IModel;

public class SummuryReportFileProcessor implements IFileReportGenerator {
	
	

	@Override
	public void process(List<? extends IModel> listOfModel) {
		List<Statement> statements = (List<Statement>) listOfModel;
		Optional<BigDecimal> totalIncome = findTotalIncome(statements);
		Optional<BigDecimal> totalExpense = findTotalExpense(statements);
		System.out.println(totalExpense);
		System.out.println(totalIncome);
		findTopExpenseCategory(statements);
		
	}

	private void findTopExpenseCategory(List<Statement> statements) {
		Map<String, Optional<BigDecimal>> mapByCategory = statements.stream()
				.filter(s->s.getAmount().signum()<0)
				.collect(Collectors.groupingBy(Statement::getDescription, 
				Collectors.mapping(Statement::getAmount, Collectors.reducing(BigDecimal::add))));
		//System.out.println(mapByCategory);
		String key = mapByCategory.entrySet().stream().max((o1,o2)->o2.getValue().get().compareTo(o1.getValue().get())).get().getKey();
		System.out.println(key+" @ "+mapByCategory.get(key).get().negate());
	}

	private Optional<BigDecimal> findTotalExpense(List<Statement> statements) {
		return statements.stream().filter(s->s.getAmount().signum()<0).map(Statement::getAmount).reduce(BigDecimal::add);
	}

	private Optional<BigDecimal> findTotalIncome(List<Statement> statements) {
		return statements.stream().filter(s->s.getAmount().signum()>0).map(Statement::getAmount).reduce(BigDecimal::add);
	}

	
	
	
}
