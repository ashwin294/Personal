/**
 * 
 */
package my.two;

import org.apache.log4j.Logger;
import org.bt.ngae.ObjectPersistenceManager.HibernateManager.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;



/**
 * @author simhachalam kolli 10 Dec 2015
 */
public class CreateSchema {

	static Logger logger = Logger.getLogger(CreateSchema.class);

	public CreateSchema() {
		Session  session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		logger.info("Entity Manager Connection Status:" + session.isOpen());
		if (!session.isOpen()) {
			logger.info("DataBase Connection Could not be successful");
		}
		else {
			tx.commit();
			logger.info("DB Tables Created Successfully");
			session.close();
		}
	}

	public static void main(String[] args) {
		logger.info("Program Execution Started");
		 new CreateSchema();
		logger.info("Program Execution Ends");
	}
}
