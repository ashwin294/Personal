package my.two;

import org.bt.ngae.ObjectPersistenceManager.HibernateManager.util.HibernateUtil;
import org.hibernate.Session;

import com.bt.ngae.hibernate.Device;

public class StoreTestData {

	public void storeDevice() {
		/*Device device = new Device(); 
        device.setDevicetype("cisco"); 
        device.setHost("SH05HQ"); 
        device.setManagementip("192.168.0.148"); 
        device.setManagementtype(0); 
        device.setOsversion("rios-9.1");*/
		
		Device device = new Device(); 
        device.setDevicetype("cisco"); 
        device.setHost("SH06HQ"); 
        device.setManagementip("192.168.0.148"); 
        device.setManagementtype(0); 
        device.setOsversion("iosxe-15.5");

		Session session = null;
		session = HibernateUtil.getSession();
		session.getTransaction().begin();
		session.save(device);
		session.getTransaction().commit();
		HibernateUtil.closeSession(session);
		System.out.println("End");
	}

	public static void main(String[] args) {
		new StoreTestData().storeDevice();
	}
}
