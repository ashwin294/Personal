package my.six;

import java.text.DecimalFormat;

public class DoubleFormat {

	
	public static void main(String[] args) {
		
		String s = Double.parseDouble("1.6E8") + "";
//		String ss = String.format("%.0f", s);
		System.out.println(Double.toString(Double.parseDouble("498484064684684")));
		System.out.println(s);
		
		DecimalFormat decimalFormat = new DecimalFormat("0.0");
		System.out.println(decimalFormat.format(Double.parseDouble("498484064684684")));
		
	}
}
