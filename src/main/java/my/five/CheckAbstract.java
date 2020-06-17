package my.five;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;



public class CheckAbstract {
	
	AtomicInteger a = new AtomicInteger(10);
	Lock lock;
	
	void m1(){
		Executors.newSingleThreadExecutor();
	}
	
}


abstract interface Abc{
	
}