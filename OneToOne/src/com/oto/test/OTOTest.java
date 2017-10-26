package com.oto.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.oto.entities.SubscriberPreferences;
import com.oto.entities.Subscriber;
import com.oto.util.HibernateUtil;

public class OTOTest {
	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		Session session = null;
		boolean flag = false;
		Subscriber subscriber = null;
		SubscriberPreferences sPreferences = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();

			subscriber = new Subscriber();
			subscriber.setSubscriberName("David");
			subscriber.setSubscriptionDate(new Date());
			subscriber.setMobileNo("3335353");
			subscriber.setPlan("CUG299");
			subscriber.setNetworkType("GSM");
			session.save(subscriber);

			sPreferences = new SubscriberPreferences();
			sPreferences.setDnd(true);
			sPreferences.setCallWaiting(true);
			sPreferences.setCallForwarding(false);
			sPreferences.setCallerIdentification(true);
			sPreferences.setSubscriber(subscriber);

			int subscriberNo = (Integer) session.save(sPreferences);
			System.out.println("subscriberno : " + subscriberNo);

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
