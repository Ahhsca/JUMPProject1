package com.cognixia.jump.projects;

public class InvalidSalaryException extends Exception {
	public InvalidSalaryException() {
		super("Salary cannnot be negative!");
	}
}
