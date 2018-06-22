package com.cme;

public class Generics {
	Thread t;
}

class P<T> {
	// static T mem; // Cannot make static reference to non-static T
	T mem;
}

class Q<T> {
	T mem;

	public Q(T arg) {
		mem = arg;
	}
}

class R<T> {
	T mem;

	public R() {
		// mem = new T(); // Cannot instantiate the type T
	}
}

class S<T> {
	T[] arr;

	public S() {
		// arr = new T[100]; // Cannot create a generic array of T
	}
}