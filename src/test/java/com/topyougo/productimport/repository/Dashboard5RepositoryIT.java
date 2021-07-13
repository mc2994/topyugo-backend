package com.topyougo.productimport.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.topyougo.productimport.model.Dashboard5;

@DataJpaTest
@ActiveProfiles("test")
class Dashboard5RepositoryIT {

	@Autowired
	private Dashboard5Repository dashboard5Repository;
	
	@Test
	//@Sql("/sql-template/topyugo-test.sql")
	void testGetDashboard5() {
		Dashboard5 model = new Dashboard5();
		model.setProduct("Master Facial Wash for Men");;
		model.setRank(1);
		model.setSales("54545");
		model.setSalesRate(2.35);
		
		dashboard5Repository.save(model);
		
		//TODO Function "DASHBOARD5" not found; SQL statement:
//		List<Dashboard5> response = dashboard5Repository.getDashboard5("8", "2020");
	}

}
