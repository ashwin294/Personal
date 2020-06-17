package com.cme;

public class ThreadFinalStringBuffer extends Thread {

	final StringBuffer sb1 = new StringBuffer();
	final StringBuffer sb2 = new StringBuffer();

	public static void main(String[] args) {
		final ThreadFinalStringBuffer h = new ThreadFinalStringBuffer();
		new Thread() {
			@Override
			public void run() {
				synchronized (this) {
					h.sb1.append("[sb1:2]");
					h.sb2.append("[sb2:2]");
					System.out.println(h.sb1);
					System.out.println(h.sb2);
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				synchronized (this) {
					h.sb1.append("[sb1:1]");
					h.sb2.append("[sb2:1]");
					System.out.println(h.sb1);
					System.out.println(h.sb2);
				}
			}
		}.start();
	}
}
