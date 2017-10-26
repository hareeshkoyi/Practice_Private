package com.ht.test;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HTTest {
	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		SchemaExport export = new SchemaExport(configuration);
		export.create(true, true);
	}
}
