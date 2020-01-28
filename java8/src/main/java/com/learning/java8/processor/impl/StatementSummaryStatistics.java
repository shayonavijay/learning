package com.learning.java8.processor.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import com.learning.java8.model.Statement;

public class StatementSummaryStatistics implements Consumer<Statement> {

	private long count;
    private BigDecimal sum =BigDecimal.ZERO;
    private BigDecimal expense = BigDecimal.ZERO;
    private BigDecimal income = BigDecimal.ZERO;
    private BigDecimal min = BigDecimal.ZERO;
    private BigDecimal max = BigDecimal.ZERO;
    private Map<String, BigDecimal> mapForCategory = new ConcurrentHashMap<>();
    
	@Override
	public void accept(Statement statement) {
		 ++count;
		 BigDecimal amount = statement.getAmount();
		 String key = statement.getDescription();
		 sum = sum.add(amount);
	     min = min.compareTo(amount)>0?amount:min;
	     max = max.compareTo(amount)>0?max:amount;
	     if(amount.signum()>0) {
	    	 income =income.add(amount);
	     } else {
	    	 expense =expense.add(amount);
	    	 BigDecimal value = mapForCategory.containsKey(key) ? mapForCategory.get(key).add(amount) : amount;
	    	 mapForCategory.put(key, value);
	     }
	}
	
	public void combine(StatementSummaryStatistics other) {
        count += other.count;
        BigDecimal otherMin = other.getMin();
        BigDecimal otherMax = other.getMax();
        sum = sum.add(other.getSum());
        min = min.compareTo(otherMin)>0?otherMin:min;
	    max = max.compareTo(otherMax)>0?max:otherMax;
	    income =income.add(other.getIncome());
	    expense =expense.add(other.getExpense());
	     
    }
	
	public Entry<String, BigDecimal> getTopExpenseCategoryEntry() {
		return mapForCategory.entrySet().stream().max((o1,o2)->o2.getValue().compareTo(o1.getValue())).get();
	}

	public long getCount() {
		return count;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public BigDecimal getExpense() {
		return expense;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public BigDecimal getMin() {
		return min;
	}

	public BigDecimal getMax() {
		return max;
	}

	public String toString() {
        return String.format(
            "%s{count=%d, "
            + "sum=%f, \n"
            + "min=%f, \n"
            + "max=%f, \n"
            + "income=%f, \n"
            + "expense=%f, \n"
            + "top expense category =%s} ",
            this.getClass().getSimpleName(),
            getCount(),
            getSum(),
            getMin(),
            getMax(),
            getIncome(),
            getExpense(),
            getTopExpenseCategoryEntry().getKey()+"@"+getTopExpenseCategoryEntry().getValue());
    }
	

}
