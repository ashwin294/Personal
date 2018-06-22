package my.two;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.hibernate.ejb.event.CallbackHandlerConsumer;

import com.bt.ngae.ConfigReader;
import com.bt.ngae.InterfaceDetails;
import com.bt.ngae.hibernate.cisco.iosxe._15._5.nodes.device.configlist.Cosconfig;
import com.bt.ngae.webserviceclient.CLIDetails;

import edu.emory.mathcs.backport.java.util.concurrent.CountDownLatch;
import javassist.bytecode.Descriptor.Iterator;

public class TestClass {


		void writePath() {
		Long a = 50l;

		System.out.println(this.getClass().getClassLoader().getResource("").getPath());
	}

	static String calculateMultiplier(String value) {
		if (value.length() < 1)
			return "";
		String number = value.replaceAll("[^\\d.]", "");
		long longValue;
		if (value.endsWith("g") || value.endsWith("G"))
			longValue = (long) (Long.parseLong(number) * Math.pow(10, 9));
		else if (value.endsWith("m") || value.endsWith("M"))
			longValue = (long) (Long.parseLong(number) * Math.pow(10, 6));
		else if (value.endsWith("k") || value.endsWith("K"))
			longValue = (long) (Long.parseLong(number) * Math.pow(10, 3));
		else if (value.endsWith("t") || value.endsWith("T"))
			longValue = (long) (Long.parseLong(number) * Math.pow(10, 12));
		else
			longValue = Long.parseLong(number);
		return Long.toString(longValue);
	}

	public static void main(String[] args) {
		
//		java.util.concurrent.CountDownLatch l = new java.util.concurrent.CountDownLatch(10);
//		try {
//			l.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		

//		System.out.println(TestClass.calculateMultiplier("78g"));
//
//		try {
//			System.out.println(ConfigReader.getInterfaceDetails(
//					"C:\\pentaho\\data-integration\\config_files\\PE28-EWR-AM-RE0.juniper-config", "juniper",
//					"PE28-EWR-AM-RE0"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// System.out.println(new
		// CLIDetails().getCliDetails("PE7-STK-EU","cisco"));
		// String filename =
		// "C:\\Users\\609572762\\Documents\\work\\WinSCP\\all_configs\\PE37-DUS-EU.cisco-config";
		// System.out.println(filename.substring(filename.lastIndexOf(File.separator)+1,
		// filename.indexOf(".cisco-config")));
		//

		// new TestClass().writePath();

		// String configFilePath = "";
		// ConfigReader configReader = null;
		// try {
		// Cosconfig cosconfig = (Cosconfig) ConfigReader
		// .getConfigObject("C:/Users/609572762/Documents/work/WinSCP/all_configs/PE5-LIB-EU.cisco-config",
		// "cisco")
		// .getResponseObject();
		// if (cosconfig == null)
		// throw new NullPointerException("No Cosconfig object returned.");
		// configReader = new ConfigReader(cosconfig);
		// List interfaceDetailsList = configReader.readConfig();
		// System.out.println(interfaceDetailsList);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		
		
		
		String a = "a/b/c/bv/f.sasa";
		System.out.println(a.substring(0,a.lastIndexOf(".")) + "_"+a.substring(a.lastIndexOf(".")));
		

	}

}
