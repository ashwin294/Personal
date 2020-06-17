package my.five;

public class Finally {
	public static void main(String[] args) {

		try {
			System.out.println(method1());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	static int method1() {
		try {
			return 0;
//			throw new Exception();
		} catch (Exception e) {
			return 1;
		} finally {
			System.out.println("finally");
		}

	}

}
