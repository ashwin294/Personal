package my.one;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.bt.ngae.objectPersistenceManager.api.ObjectPersistenceManager;
import org.bt.ngae.objectPersistenceManager.api.ObjectPersistenceManagetImpl;

import com.bt.activation.response.ServiceResponse;
import com.bt.ngae.formatter.MultiLineCliFormatter;
import com.bt.ngae.hibernate.Device;
import com.bt.ngae.hibernate.cisco.iosxe._15._5.nodes.device.configlist.Cosconfig;
import com.bt.ngae.hibernate.cisco.iosxe._15._5.nodes.device.configlist.cosconfig.Interface;
import com.bt.ngae.hibernate.cisco.iosxe._15._5.nodes.device.configlist.cosconfig.Policymap;
import com.bt.ngae.hibernate.cisco.iosxe._15._5.nodes.device.configlist.cosconfig._interface.Servicepolicy;
import com.bt.ngae.hibernate.cisco.iosxe._15._5.nodes.device.configlist.cosconfig.policymap.Classcos;
import com.bt.ngae.hibernate.cisco.iosxe._15._5.nodes.device.configlist.cosconfig.policymap.classcos.Policyactions;
import com.bt.ngae.hibernate.cisco.iosxe._15._5.types.Policyaction;
import com.bt.ngae.parser.api.ConfigParserAPI;
import com.bt.ngae.parser.api.ConfigParserAPIImpl;
import com.bt.ngae.parser.utils.CommonFunctions;

import my.one.InterfaceDetails;

public class ConfigReader {

	private static Logger log = Logger.getLogger(ConfigReader.class);

	private ArrayList<InterfaceDetails> interfaceDetailsList = new ArrayList<>();
	private List<Interface> interfaceList;
	private List<Policymap> policymapList;

	public ConfigReader(Cosconfig cosconfig) {
		this.interfaceList = cosconfig.getInterface();
		this.policymapList = cosconfig.getPolicymap();
	}

	public Policymap getPolicymap(String policyMapName) {
		for (Policymap policymap : this.policymapList)
			if (policyMapName.equals(policymap.getPolicymapname()))
				return policymap;
		return null;
	}

	public Interface getInterface(String interfaceName) {
		for (Interface _interface : interfaceList)
			if (interfaceName.equals(_interface.getInterfaceName()))
				return _interface;
		return null;
	}

	public void setCosClassDetails(String cosClassName, String cosValue, InterfaceDetails interfaceDetails) {
		String localCosClassName = cosClassName.trim();
		String expertLevel = "Expert";
		switch (localCosClassName) {
		case "pe_af1_in_input":
			interfaceDetails.setAf1Cipr(cosValue);
			calculateCosCustomisationLevel(interfaceDetails);
			break;
		case "pe_af2_in_input":
			interfaceDetails.setAf2Cipr(cosValue);
			calculateCosCustomisationLevel(interfaceDetails);
			break;
		case "pe_af3_in_input":
			interfaceDetails.setAf3Cipr(cosValue);
			calculateCosCustomisationLevel(interfaceDetails);
			break;
		case "pe_af4_in_input":
			interfaceDetails.setAf4Cipr(cosValue);
			calculateCosCustomisationLevel(interfaceDetails);
			break;
		case "pe_af1_output":
			interfaceDetails.setAf1Mipr(cosValue);
			interfaceDetails.setCosCustomisationLevel(expertLevel);
			break;
		case "pe_af2_output":
			interfaceDetails.setAf2Mipr(cosValue);
			interfaceDetails.setCosCustomisationLevel(expertLevel);
			break;
		case "pe_af3_output":
			interfaceDetails.setAf3Mipr(cosValue);
			interfaceDetails.setCosCustomisationLevel(expertLevel);
			break;
		case "pe_af4_output":
			interfaceDetails.setAf4Mipr(cosValue);
			interfaceDetails.setCosCustomisationLevel(expertLevel);
			break;
		case "pe_mgmt_bun_input":
			interfaceDetails.setBundled("Bundled");
			interfaceDetails.setMgmtCipr(cosValue);
			break;
		case "pe_mgmt_unb_input":
			interfaceDetails.setBundled("Unbundled");
			interfaceDetails.setMgmtCipr(cosValue);
			break;
		case "pe_mgmt_bun_output":
			interfaceDetails.setBundled("Bundled");
			interfaceDetails.setMgmtMipr(cosValue);
			// interfaceDetails.setCosCustomisationLevel("Expert");
			break;
		case "pe_mgmt_unb_output":
			interfaceDetails.setBundled("Unbundled");
			interfaceDetails.setMgmtMipr(cosValue);
			// interfaceDetails.setCosCustomisationLevel("Expert");
			break;
		case "demipr":
			interfaceDetails.setDeMipr(cosValue);
			break;
		default:
			log.info("Unrecognised cos class " + localCosClassName + " " + interfaceDetails.getInterfaceName());
			break;
		}
	}

	private void calculateCosCustomisationLevel(InterfaceDetails interfaceDetails) {
		String cosCustomisationLevel = interfaceDetails.getCosCustomisationLevel();
		if (cosCustomisationLevel.length() < 1)
			interfaceDetails.setCosCustomisationLevel("Standard");
		else if ("Standard".equalsIgnoreCase(cosCustomisationLevel))
			interfaceDetails.setCosCustomisationLevel("Advanced");
	}

	public List<InterfaceDetails> readConfig() {
		try {
			InterfaceDetails interfaceDetails;
			for (Interface _interface : interfaceList) {
				interfaceDetails = new InterfaceDetails();

				// Cos Model is level 6 for ASR PEs
				interfaceDetails.setCosModel("6 Level");

				// Set Interface name - port, vlan ...
				getInterfaceName(_interface, interfaceDetails);

				// Set COS values cipr, mipr ...
				getCosValues(_interface, interfaceDetails);

				this.interfaceDetailsList.add(interfaceDetails);

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return this.interfaceDetailsList;
	}

	public void getCosValues(Interface _interface, InterfaceDetails interfaceDetails) {
		String interfaceName;
		if (_interface == null || (interfaceName = _interface.getInterfaceName()) == null || interfaceName.length() < 1)
			return;

		// Input service policy
		try {
			Policymap inputServicepolicy = getPolicymap(_interface.getServicepolicy().getInput());
			for (Classcos classcos : inputServicepolicy.getClasscos()) {
				try {
					String cosClassName;
					if (((cosClassName = classcos.getName()) != null) && cosClassName.length() > 0
					/* && !"class-default".equals(cosClassName) */) {
						// Set cos values
						setAllCosValues(classcos, interfaceDetails, Policyaction.POLICE);
					}
				} catch (Exception exception) {
					log.info("Error in reading Parent cos details for " + interfaceDetails.getInterfaceName() + " : "
							+ classcos.getName(), exception);
				}
			}
		} catch (NullPointerException nullPointerException) {
			log.info("No input ServicePolicy found for " + interfaceName);
		} catch (Exception exception) {
			log.info("Error in reading Parent Input ServicePolicy for " + interfaceName, exception);
		}

		// Output service policy
		try {
			Policymap outputServicepolicy = getPolicymap(_interface.getServicepolicy().getOutput());
			for (Classcos classcos : outputServicepolicy.getClasscos()) {
				try {
					String cosClassName;
					if (((cosClassName = classcos.getName()) != null) && cosClassName.length() > 0) {
						String cosValue;
						// Get Bandwidth
						if ("class-default".equals(cosClassName)) {
							try {
								cosValue = "" + getPolicyactionsFor(classcos.getPolicyactions(), Policyaction.SHAPE)
										.getShapeoption().getShape().getAverage().getBitrates().getBitrate();
								interfaceDetails.setBandwidth(cosValue);
							} catch (Exception exception) {
								log.info("Could not read Bandwidth for " + interfaceDetails.getInterfaceName() + " "
										+ classcos.getName(), exception);
							}
						}

						// Set other cos values
						setAllCosValues(classcos, interfaceDetails, Policyaction.BANDWIDTH);
					}
				} catch (Exception exception) {
					log.info("Error in reading Parent cos details for" + interfaceDetails.getInterfaceName() + " : "
							+ classcos.getName(), exception);
				}
			}
		} catch (NullPointerException nullPointerException) {
			log.info("No output ServicePolicy found for " + interfaceName);
		} catch (Exception exception) {
			log.info("Error in reading Parent Output ServicePolicy for " + interfaceName, exception);
		}
	}

	private void setAllCosValues(Classcos classcos, InterfaceDetails interfaceDetails, Policyaction policyaction) {
		String cosValue = "";
		String cosClassName = classcos.getName();
		Policyactions policyactions = null;
		// Get other cos values in Parent Servicepolicy
		try {
			setEnableWred(interfaceDetails, classcos.getPolicyactions());
			if ((policyactions = getPolicyactionsFor(classcos.getPolicyactions(), policyaction)) != null) {
				if (policyaction.equals(Policyaction.BANDWIDTH)) { // OUT
					if ("class-default".equals(cosClassName))
						cosClassName = "demipr";
					cosValue = "" + policyactions.getBandwidthoption().getBandwidth().getBandwithinratio()
							.getRemaining().getRatio();
					setCosClassDetails(cosClassName, cosValue, interfaceDetails);
				} else if (policyaction.equals(Policyaction.POLICE)) { // IN
					if ("class-default".equals(cosClassName))
						setCosBleaching(classcos, interfaceDetails);
					else {
						cosValue = "" + policyactions.getPoliceoption().getPolice().getIncontractbw();
						setCosClassDetails(cosClassName, cosValue, interfaceDetails);
					}
				}
			}
		} catch (Exception exception) {
			log.info("Error in reading cos details for " + interfaceDetails.getInterfaceName() + " : "
					+ classcos.getName(), exception);
		}

		// Check if this Classcos has child service policy
		String policyMapName;
		if ((policyactions = getPolicyactionsFor(classcos.getPolicyactions(), Policyaction.SERVICEPOLICY)) != null
				&& ((policyMapName = policyactions.getServicepolicyoption().getServicepolicy()) != null)
				&& policyMapName.length() > 0) {
			Policymap policymap = getPolicymap(policyMapName);
			if (policymap != null) {
				for (Classcos classcos1 : policymap.getClasscos()) {
					try {
						// Recursive call to read child cos details
						setAllCosValues(classcos1, interfaceDetails, policyaction);
					} catch (Exception exception) {
						log.info("Error in reading cos details for " + interfaceDetails.getInterfaceName() + " : "
								+ classcos1.getName(), exception);
					}
				}
			} else {
				log.info("Couldn't find Servicepolicy object for policyMapName " + policyMapName);
			}
		}
	}

	private void setCosBleaching(Classcos classcos, InterfaceDetails interfaceDetails) {
		String dscpValue;
		try {
			Policyactions policyactions = getPolicyactionsFor(classcos.getPolicyactions(), Policyaction.SET);
			if (policyactions != null && (dscpValue = policyactions.getSetoption().getSet().getIp().getDscp()) != null
					&& !"0".equals(dscpValue))
				interfaceDetails.setCosBleaching("Y");
			else if (!"Y".equalsIgnoreCase(interfaceDetails.getCosBleaching()))
				interfaceDetails.setCosBleaching("N");
		} catch (Exception exception) {
			log.info("Could not set Cos Bleaching option for " + interfaceDetails.getInterfaceName());
		}
	}

	private void setEnableWred(InterfaceDetails interfaceDetails, List<Policyactions> policyactionsList) {
		Policyactions policyactions = null;
		try {
			policyactions = getPolicyactionsFor(policyactionsList, Policyaction.RANDOMDETECT);
			if (policyactions != null && policyactions.getRandomdetectoption().getRandomdetect() != null)
				interfaceDetails.setEnableWred("Y");
		} catch (Exception exception) {
			// Do nothing
		}
		// for (Policyactions policyactions : policyactionsList) {
		// try {
		// if (policyactions.getActiontype().equals(Policyaction.RANDOMDETECT)
		// && policyactions.getRandomdetectoption().getRandomdetect() != null)
		// if (policyactions.getRandomdetectoption().getRandomdetect() != null)
		// interfaceDetails.setEnableWred("Y");
		// }
	}

	private void getInterfaceName(Interface _interface, InterfaceDetails interfaceDetails) {
		String interfaceName = _interface.getInterfaceName();
		if (interfaceName == null || "".equals(interfaceName))
			return;
		interfaceDetails.setInterfaceName(interfaceName);
		try {
			Servicepolicy servicepolicy = _interface.getServicepolicy();
			if (interfaceName.contains(".")) {
				// If sub-interface
				int dotSeparator = interfaceName.indexOf('.');
				interfaceDetails.setVlanId(interfaceName.substring(dotSeparator + 1));
				interfaceDetails.setPortName(interfaceName.substring(0, dotSeparator));
				interfaceDetails.setVrfName(_interface.getVrf().getForwarding());
				if (servicepolicy != null
						&& ((servicepolicy.getInput() != null && servicepolicy.getInput().length() > 0)
								|| (servicepolicy.getOutput() != null && servicepolicy.getOutput().length() > 0))) {
					interfaceDetails.setCosAssignmenttype("CosPerConnection");
				}
			} else {
				interfaceDetails.setPortName(interfaceName);
				if (servicepolicy != null
						&& ((servicepolicy.getInput() != null && servicepolicy.getInput().length() > 0)
								|| (servicepolicy.getOutput() != null && servicepolicy.getOutput().length() > 0))) {
					interfaceDetails.setCosAssignmenttype("CosPerAccess");
				}
			}
		} catch (Exception exception) {
			log.info("Error in reading interface details for " + interfaceName, exception);
		}
	}

	Policyactions getPolicyactionsFor(List<Policyactions> policyActionsList, Policyaction policyAction) {
		if (policyAction.equals(Policyaction.BANDWIDTH))
			for (Policyactions policyactions : policyActionsList) {
				if (policyactions.getBandwidthoption() != null)
					return policyactions;
			}
		else if (policyAction.equals(Policyaction.POLICE))
			for (Policyactions policyactions : policyActionsList) {
				if (policyactions.getPoliceoption() != null)
					return policyactions;
			}
		else if (policyAction.equals(Policyaction.SHAPE))
			for (Policyactions policyactions : policyActionsList) {
				if (policyactions.getShapeoption() != null)
					return policyactions;
			}
		else if (policyAction.equals(Policyaction.RANDOMDETECT))
			for (Policyactions policyactions : policyActionsList) {
				if (policyactions.getRandomdetectoption() != null)
					return policyactions;
			}
		else if (policyAction.equals(Policyaction.SERVICEPOLICY))
			for (Policyactions policyactions : policyActionsList) {
				if (policyactions.getServicepolicyoption() != null)
					return policyactions;
			}
		else if (policyAction.equals(Policyaction.SET))
			for (Policyactions policyactions : policyActionsList) {
				if (policyactions.getSetoption() != null)
					return policyactions;
			}

		// if (policyactions.getActiontype().equals(policyAction))
		// return policyactions;

		return null;
	}

	String getBasicDetails(InterfaceDetails interfaceDetails) {
		return "" + interfaceDetails.getInterfaceName();
	}

	public static ServiceResponse getConfigObject(String configLocation, String type) throws Exception {
		ServiceResponse serviceResponse = null;
		type = type.toLowerCase();
		String hostname = getHostnameFromFile(configLocation, type);
		String preprocessedFile = "PRE-PROCESSED_" + hostname + "_" + type + ".txt";
		// String configLocation =
		// "C:/Users/609572762/Documents/work/WinSCP/all_configs/";
		// String extension = null;
		// if ("cisco".equalsIgnoreCase(type))
		// extension = ".cisco-config";
		// else if ("juniper".equalsIgnoreCase(type))
		// extension = ".juniper-config";
		ConfigParserAPI objConfigParser = new ConfigParserAPIImpl();
		InputStream inputS;
		String serviceId = "40";
		String serviceName = "COS";
		try {
			ObjectPersistenceManager obj = new ObjectPersistenceManagetImpl();
			MultiLineCliFormatter formatter = new MultiLineCliFormatter();
			Device objDevice = (Device) obj.getDeviceContext("SH06HQ");
			inputS = new FileInputStream(new File(configLocation));
			String preprocessedCli = formatter.formatCli(inputS);
			if (preprocessedCli.contains("FAILURE")) {
				log.info(preprocessedCli);
				return null;
			}
//			CommonFunctions.writeFileForTest(preprocessedCli, preprocessedFile);
			InputStream stream = IOUtils.toInputStream(preprocessedCli);
			// String result = (String) objConfigParser.parseConfig(stream,
			// objDevice, serviceId, serviceName, false).getStatus();
			// log.info"Result is: " + result);
			// String
			// result=(String)objConfigParser.parseConfig(inputS,objDevice,serviceId,serviceName,false).getStatus();
			serviceResponse = objConfigParser.parseConfig(stream, objDevice, serviceId, serviceName, false);
			log.info("Successfully received Config Object.");
		} catch (Exception exception) {
			log.info("Could not get Config object.");
			throw exception;
		}
		return serviceResponse;
	}

	public static String getHostnameFromFile(String filename, String routerType) {
		String hostname = null;
		try {
			hostname = filename.substring(filename.lastIndexOf(File.separator) + 1,
					filename.indexOf("." + routerType.toLowerCase() + "-config"));
		} catch (Exception exception) {
			log.info("Could not extract hostname from string " + filename, exception);
		}
		return hostname;
	}

	private static String getLocalConfig(String hostname, String routerType) {
		String configLocation = "C:/Users/609572762/Documents/work/WinSCP/all_configs";
		String configFilePath = null;
		File configFile = new File(configLocation + File.separator + hostname + "." + routerType + "-config");
		if (configFile.exists())
			configFilePath = configFile.getAbsolutePath();
		return configFilePath;
	}

	public static void main(String[] args) {
		try {
			String hostname = "PE5-LIB-EU";
			String routerType = "cisco";
			String filePath = getLocalConfig(hostname, routerType);
			ConfigReader configReader = new ConfigReader(
					(Cosconfig) getConfigObject(filePath, routerType).getResponseObject());
			List<InterfaceDetails> interfaceDetailsList = configReader.readConfig();
			log.info(interfaceDetailsList);
		} catch (Exception exception) {
			log.error(">> ERROR", exception);
		}
	}

}
