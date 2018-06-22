package com.cme;

public class Synchronization {
	
	public static void main(String[] args) {
		Object o = new Object();
		synchronized (o) {
			System.out.println("1");
			o = null;
			System.out.println("3");
		}
	}

}
