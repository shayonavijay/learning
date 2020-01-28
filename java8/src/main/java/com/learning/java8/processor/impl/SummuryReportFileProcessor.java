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
		
		
		
		/**
			Below Three methods return for find totalExpense,TotalIncome and top expense category.
			Drawback of above approach (3 methods) is performance as need to iterate collection each time.
	    
		Optional<BigDecimal> totalIncome = findTotalIncome(statements);
		Optional<BigDecimal> totalExpense = findTotalExpense(statements);
		System.out.println(totalExpense);
		System.out.println(totalIncome);
		findTopExpenseCategory(statements); **/
		
		//Below approach - create statistics class and iterate once; cleaner solution
		StatementSummaryStatistics statistics =  printStatementSummaryStatistics(statements);
		System.out.println("Statistics :: "+statistics);
		
		
		
		
	}

	private StatementSummaryStatistics printStatementSummaryStatistics(List<Statement> statements) {
		return statements.stream().collect(StatementSummaryStatistics::new, StatementSummaryStatistics::accept,
				StatementSummaryStatistics::combine); 
		
	}

	/**
		Below Three methods return for find totalExpense,TotalIncome and top expense category.
		Drawback of above approach (3 methods) is performance as need to iterate collection each time.
	**/
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
