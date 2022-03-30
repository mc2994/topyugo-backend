package com.topyougo.productimport.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.topyougo.productimport.model.Dashboard2;

class Dashboard2RepositoryIT extends BaseRepositoryTestConfig {

	@Autowired
	private Dashboard2Repository dashBoard2Repository;
	
	
	@Disabled
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
