package my.six;

public class OddEven {
	static Object lockObject = new Object();

	public static void main(String[] args) {

		new Thread(new OddThread()).start();
		synchronized (OddEven.lockObject) {
		}
		new Thread(new EvenThread()).start();
	}

}

class OddThread implements Runnable {

	@Override
	public void run() {

		for (int i = 1; i <= 100; i++) {
			synchronized (OddEven.lockObject) {
				if (i % 2 == 1) {
					System.out.println(i);
					try {
						OddEven.lockObject.notify();
						OddEven.lockObject.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

}

class EvenThread implements Runnable {

	@Override
	public void run() {

		for (int i = 1; i <= 100; i++) {
			synchronized (OddEven.lockObject) {
				if (i % 2 == 0) {
					System.out.println(i);
					try {
						OddEven.lockObject.notify();
						OddEven.lockObject.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

}
