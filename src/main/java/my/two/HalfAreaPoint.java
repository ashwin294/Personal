package my.two;
import java.util.Scanner;

public class HalfAreaPoint {
	public double findHalfAreaPoint(Curve curve, double p) {
		double start = 0;
		double end = p;
		double totalArea = Math.round(curve.areaUnderCurve(p) * 1000d);
		double expectedArea = totalArea / 2;
		double area = 0;
		double areaRounded = 0;
		while (start <= end) {
			double mid = (start + end) / 2; // round here
			area = curve.areaUnderCurve(mid);
			areaRounded = Math.round(area * 1000d);
			if (expectedArea == areaRounded) {
				return mid;
			}
			if (areaRounded > expectedArea) {
				end = mid - 0.001;
			} else {
				start = mid + 0.001;
			}
		}
		return -1;

	}
	
	public int binarySearch(int val, int key) {
        
		Math.round(val*1000d);
		
        int start = 0;
        int end = key;
        while (start <= end) {
            int mid = (start + end) / 2;   		//round here
            if (key == val) {
                return mid;
            }
            if (key < val) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
	
	

    // DO NOT MODIFY CODE BELOW THIS LINE
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");
			Curve c = null;
			switch (tokens[0]) {
			case "LINE":
				c = new Line(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
				break;
			case "EXP":
				c = new Exp();
				break;
			case "POWER":
				c = new Power(Double.parseDouble(tokens[1]));
				break;
			}

			if (c == null) {
				break;
			}
			HalfAreaPoint t=new HalfAreaPoint();
			double p = 10.0d;
			double h = t.findHalfAreaPoint(c, p);
			System.out.println(Math.round(h*1000d));
		}

		scanner.close();
	}
}

	interface Curve {
		double areaUnderCurve(double x);
	}

	class Line implements Curve {
		private double m;
		private double c;

		public Line(double m, double c) {
			this.m = m;
			this.c = c;
		}

		@Override
		public double areaUnderCurve(double x) {
			return m * x + c;
		}
	}

	class Exp implements Curve {
		@Override
		public double areaUnderCurve(double x) {
			return Math.exp(x);
		}
	}

	class Power implements Curve {
		private double power;

		public Power(double power) {
			this.power = power;
		}

		@Override
		public double areaUnderCurve(double x) {
			return Math.pow(x, power);
		}
	}