package com.learning.java8.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.learning.java8.util.IModel;

public class Statement extends IModel {

	private LocalDate date;
	private BigDecimal amount;
	private String description;
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Statement(LocalDate date, BigDecimal amount, String description) {
		super();
		this.date = date;
		this.amount = amount;
		this.description = description;
	}
	
	
	
	
}
