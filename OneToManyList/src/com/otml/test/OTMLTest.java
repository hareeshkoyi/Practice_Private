package com.otml.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.otml.entities.ServiceRequest;
import com.otml.entities.Technician;
import com.otml.util.HibernateUtil;

public class OTMLTest {
	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		Session session = null;
		boolean flag = false;
		Technician technician1 = null;
		Technician technician2 = null;
		List<ServiceRequest> serviceRequests1 = null;
		List<ServiceRequest> serviceRequests2 = null;
		ServiceRequest sr1 = null;
		ServiceRequest sr2 = null;
		ServiceRequest sr3 = null;
		ServiceRequest sr4 = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			sr1 = newServiceRequest("Samsung Galaxy S7 blasted", new Date(), 1,
					2, "open");
			sr2 = newServiceRequest("Motorolo G4Plus hanged", new Date(), 1, 2,
					"open");
			sr3 = newServiceRequest("Lenova battery backup issue", new Date(),
					2, 1, "open");
			sr4 = newServiceRequest("Appo not working", new Date(), 3, 10,
					"suspended");
			session.save(sr1);
			session.save(sr2);
			session.save(sr3);
			session.save(sr4);

			technician1 = newTechnician("David", "Engineer", "394894",
					"david@sr.com");
			serviceRequests1 = new ArrayList<ServiceRequest>();
			serviceRequests1.add(sr4);
			serviceRequests1.add(sr2);
			technician1.setServiceRequests(serviceRequests1);
			session.save(technician1);

			technician2 = newTechnician("John", "Senior Engineer", "393904",
					"john@sr.com");
			serviceRequests2 = new ArrayList<ServiceRequest>();
			serviceRequests2.add(sr1);
			serviceRequests2.add(sr3);
			technician2.setServiceRequests(serviceRequests2);
			session.save(technician2);

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

	private static ServiceRequest newServiceRequest(String pDescription,
			Date rDate, int priority, int eta, String status) {
		ServiceRequest sr = new ServiceRequest();
		sr.setProblemDescription(pDescription);
		sr.setServiceRequestDate(rDate);
		sr.setPriority(priority);
		sr.setEta(eta);
		sr.setStatus(status);
		return sr;
	}

	private static Technician newTechnician(String name, String designation,
			String contactNo, String emailAddress) {
		Technician technician = null;

		technician = new Technician();
		technician.setName(name);
		technician.setDesignation(designation);
		technician.setContactNo(contactNo);
		technician.setEmailAddress(emailAddress);

		return technician;
	}
}
