package com.cme;

public class Inheritence1 {
	public static void main(String[] args) {

		B0 b = new B0();
		B1 b1 = new B1();
		B2 b2 = new B2();
		// b = b1;
		// b2 = b;
		// b1 = (B1) b;
		b2 = (B2) b;
		// b1.bMethod();

	}
}

class B0 {

}

class B1 extends B0 {

	void bMethod() {
		System.out.println("B1.bMethod()");
	}

}

class B2 extends B0 {

}