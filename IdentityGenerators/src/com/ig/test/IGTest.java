package com.ig.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ig.entities.Appointment;
import com.ig.util.HibernateSessionFactory;

public class IGTest {
	public static void main(String[] args) {
		Transaction transaction = null;
		SessionFactory sfactory = null;
		Session session = null;
		Appointment appointment = null;
		boolean flag = false;

		try {
			sfactory = HibernateSessionFactory.getSessionFactory();
			session = sfactory.openSession();
			transaction = session.beginTransaction();


			for (int i = 0; i < 1; i++) {
				appointment = new Appointment();
				appointment.setAppointmentDate(new Date());
				appointment.setDoctor("Rama raju");
				appointment.setPatientName("Subbu");
				appointment.setComments("Fever");
				appointment.setStatus("ALLOTED");
				int appointmentNo = (Integer) session.save(appointment);
				System.out.println("appointmentNo : " + appointmentNo);
			}
			

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
