package my.three;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.RandomAccessFile;

import org.apache.log4j.chainsaw.Main;

class Test {

	public static void main(String... args) {

		byte b = 65;

		switch (b) {

		case 1:
			break;
		default:
			break;

		case 'A':
			System.out.println("aaa");

			break;

		}
		try {
			RandomAccessFile f = new RandomAccessFile("aa.txt", "rw");

			f.writeBoolean(true);
			f.writeInt(123456);
			f.writeInt(7890);
			f.writeLong(100000);
			f.writeInt(777);
			f.writeFloat(.0001f);

			f.seek(5);
			System.out.println(f.readInt());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
