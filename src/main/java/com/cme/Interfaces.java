package com.cme;

interface A {
	void aMethod();
}

interface B {
	void bMethod();
}

interface C extends A, B {
	void cMethod();
}

class D implements B {

	@Override
	protected void finalize() throws Throwable {
		System.out.println("finished waiting..");
		Thread.sleep(10000);
	}

	@Override
	public void bMethod() {
		System.out.println("D.bMethod()");
	}

}

class E extends D implements C {

	@Override
	public void aMethod() {
		System.out.println("E.aMethod()");
	}

	@Override
	public void bMethod() {
		D d = new D();
		System.out.println("E.bMethod()" + d + "::" + d.hashCode());
	}

	@Override
	public void cMethod() {
		System.out.println("E.cMethod()");
	}

}

public class Interfaces {
	public static void main(String[] args) {
		B e = (D) new E();
		e.bMethod();
	}
}
