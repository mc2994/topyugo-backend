package com.topyougo.productimport.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.topyougo.productimport.model.Dashboard2;

@DataJpaTest
@ActiveProfiles("test")
class Dashboard2RepositoryIT {

	@Autowired
	private Dashboard2Repository dashBoard2Repository;
	
	
	@Test
	//@Sql("/sql-template/topyugo-test.sql")
	void testGetDashboard2() {
		Dashboard2 model = new Dashboard2();
		model.setAmount("250");
		model.setTotalOrders("25");
		model.setMonth(8);
		model.setYear(2020);
		
		dashBoard2Repository.save(model);
		
		//TODO Function "DASHBOARD2" not found; SQL statement:
		
		//dashBoard2Repository.getDashboard2("8", "2020");
	}

}
