package my.one;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.bt.ngae.objectPersistenceManager.api.ObjectPersistenceManager;
import org.bt.ngae.objectPersistenceManager.api.ObjectPersistenceManagetImpl;

import com.bt.activation.response.ServiceResponse;
import com.bt.ngae.formatter.MultiLineCliFormatter;
import com.bt.ngae.hibernate.Device;
import com.bt.ngae.parser.api.ConfigParserAPI;
import com.bt.ngae.parser.api.ConfigParserAPIImpl;
import com.bt.ngae.parser.utils.CommonFunctions;
import com.bt.ngae.parser.utils.Constants;
import com.bt.ngae.utils.CommonFunction;

public class Executer {

	public static void main(String[] args) {
		getConfigObject("PE22-PLG-EU", "cisco");
	}

	private static void getConfigObject(String hostname, String type) {
		String preprocessedFile = "PRE-PROCESSED_" + hostname + ".txt";
		String configLocation = "C:/Users/609572762/Documents/work/WinSCP/all_configs/";
		ConfigParserAPI objConfigParser = new ConfigParserAPIImpl();
		InputStream inputS;
		String serviceId = "40";
		String serviceName = "COS";
		try {
			ObjectPersistenceManager obj = new ObjectPersistenceManagetImpl();
			MultiLineCliFormatter formatter = new MultiLineCliFormatter();

			Device objDevice = (Device) obj.getDeviceContext("SH06HQ");
			inputS = new FileInputStream(new File(configLocation + hostname + ".cisco-config"));
			String preprocessedCli = formatter.formatCli(inputS);
			if (preprocessedCli.contains("FAILURE")) {
				System.out.print(preprocessedCli);
				return;
			}
			CommonFunctions.writeFileForTest(preprocessedCli, preprocessedFile);
			InputStream stream = IOUtils.toInputStream(preprocessedCli);

			// String result = (String) objConfigParser.parseConfig(stream,
			// objDevice, serviceId, serviceName, false)
			// .getStatus();
			// System.out.println("Result is: " + result);
			// String result=(String)objConfigParser.parseConfig(inputS,
			// objDevice,serviceId,serviceName,false).getStatus();

			ServiceResponse serviceResponse = objConfigParser.parseConfig(stream, objDevice, serviceId, serviceName, false);

			System.out.println("Done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
