package com.cme;

public class Inheritence {

	public static void main(String[] args) {

		new Son();

	}
}

class Man {
	public Man() {
		System.out.println("Man");
	}
}

class Father extends Man {
	public Father(String type) {
		System.out.println(type);
	}
}

class Son extends Father {
	public Son() {
		super("Father");
		new Father("Son");
	}
}
