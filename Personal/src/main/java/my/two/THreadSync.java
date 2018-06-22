package my.two;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class THreadSync {

	ConcurrentHashMap<String, String> aa;
	public static int a = 0;
	public static String s = new String("ssss");

	public static void main(String[] args) {
		new Thread(new MyThreads(0)).start();
		new Thread(new MyThreads(1)).start();
	}

}

class MyThreads implements Runnable {

	int a;
	public MyThreads(int a) {
		this.a = a;
	}

	@Override
	public void run() {
		while (THreadSync.a < 999) {
			synchronized (SQLException.class) {
				if (THreadSync.a % 2 != this.a) {
					try {
						System.out.println(this.a + " is waiting");
						SQLException.class.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println(THreadSync.a++);
				System.out.println(a + " is about to notify and sleep.");
				SQLException.class.notifyAll();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(a + " has slept.");
			}
		}

	}
}