package com.ig.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ig.entities.Event;
import com.ig.util.HibernateSessionFactory;

public class JPAIGTest {
	public static void main(String[] args) {
		Transaction transaction = null;
		SessionFactory sfactory = null;
		Session session = null;
		boolean flag = false;
		Event event = null;

		try {
			sfactory = HibernateSessionFactory.getSessionFactory();
			session = sfactory.openSession();
			transaction = session.beginTransaction();
			event = new Event();
			event.setTitle("Meeting with QA");
			event.setDescription("DHQA Meeting");
			event.setEventDate(new Date());
			event.setPlace("HYD04121");

			int eventId = (Integer) session.save(event);
			System.out.println("id : " + eventId);

			flag = true;
		} finally {
			if (transaction != null) {
				if (flag == true) {
					transaction.commit();
				} else {
					transaction.rollback();
				}
				session.close();
			}
			HibernateSessionFactory.closeSessionFactory();

		}
	}
}
