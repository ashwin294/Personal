package my.three;

import java.util.ArrayList;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {
		List <? extends RuntimeException> aa = new ArrayList<ArithmeticException>(); 
//		aa.add(new ArithmeticException());
		
		List<ArithmeticException> a1 = null;
		a1.add(new ArithmeticException());
		
		aa=a1;
		
		List <? super RuntimeException> aa2 = new ArrayList<>();
		aa2.add(new ArithmeticException());
		
		
		
		
	}
	
	
	public class MyClass<T>{
		T t;
		
//		public <A super RuntimeException > A method1(){
}
		
	}
	
