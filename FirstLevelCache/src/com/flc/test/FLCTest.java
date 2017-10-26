package com.flc.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.flc.entities.Product;
import com.flc.util.HibernateSessionFactory;

public class FLCTest {
	public static void main(String[] args) {
		Transaction transaction = null;
		SessionFactory sfactory = null;
		Session session = null;
		Product product = null;
		boolean flag = false;

		try {
			sfactory = HibernateSessionFactory.getSessionFactory();
			session = sfactory.openSession();
			/*transaction = session.beginTransaction();
			product = new Product();

			product.setProductName("Samsung LED Tv");
			product.setDescription("LED 3D Television");
			product.setMerchantName("Samsung");
			product.setPrice(355353.34f);

			session.save(product);*/
			product = (Product) session.get(Product.class, 1);
			System.out.println("Name : " + product.getProductName());
			//session.close();
			//session = sfactory.openSession();
			//session.evict(product);
			session.clear();
			Product product1 = (Product) session.get(Product.class, 1);
			System.out.println("Name : " + product1.getProductName());
			
			System.out.println("product == product1 : " + (product==product1));
			
			flag = true;
		} finally {
			if (session != null) {
				/*if (flag == true) {
					transaction.commit();
				} else {
					transaction.rollback();
				}*/
				session.close();
			}
			HibernateSessionFactory.closeSessionFactory();
		}
	}
}
