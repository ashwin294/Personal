package org.assignment.entities;

public enum RequestType {

	POST("POST"), GET("GET");

	private final String type;

	RequestType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}

}
