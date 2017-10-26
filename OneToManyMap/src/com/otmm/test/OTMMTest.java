package com.otmm.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.otmm.entities.Cargo;
import com.otmm.entities.Load;
import com.otmm.util.HibernateUtil;

public class OTMMTest {
	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		Session session = null;
		boolean flag = false;
		Cargo cargo = null;
		Load load1 = null;
		Load load2 = null;
		Map<String, Load> loads = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();

			load1 = new Load();
			load1.setDescription("Customer Parcels");
			load1.setLoadDate(new Date());
			load1.setOwner("Bluedart");
			load1.setWeight(242);
			session.save(load1);

			load2 = new Load();
			load2.setDescription("Electronics");
			load2.setLoadDate(new Date());
			load2.setOwner("Dell");
			load2.setWeight(2423);
			session.save(load2);

			cargo = new Cargo();
			cargo.setCargoName("Eastern Cargo");
			cargo.setCargoType("Big Boy");
			cargo.setCapacity(3949);
			loads = new HashMap<String, Load>();
			loads.put("S394", load1);
			loads.put("S3994", load2);
			cargo.setLoads(loads);
			session.save(cargo);

			flag = true;
		} finally {
			if (transaction != null) {
				if (flag) {
					transaction.commit();
				} else {
					transaction.rollback();
				}
			}
			HibernateUtil.closeSessionFactory();
		}

	}
}
