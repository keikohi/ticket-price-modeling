package com.example.demo.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {
	private List<Error> erros = new ArrayList<>();
	
	public void add(String message) {
		this.erros.add(new Error(message, null));
	}
	
	public void add(String message, Exception e) {
		this.erros.add(new Error(message, e));
	}
	
	public String  errorMessage() {
		return this.erros.stream()
				.map(e -> e.message)
				.collect(Collectors.joining(","));
	}
	
	public boolean hasErrors() {
		return !this.erros.isEmpty();
	}
	
	private static class Error{
		final String message;
		final Exception  cause;
		
		private Error(String message, Exception e) {
			this.message = message;
			this.cause = e;
		}
	}
}
