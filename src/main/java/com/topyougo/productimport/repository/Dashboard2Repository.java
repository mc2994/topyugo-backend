package com.topyougo.productimport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.topyougo.productimport.model.Dashboard2;

public interface Dashboard2Repository extends JpaRepository<Dashboard2, Integer> {
	
	@Query(value = "CALL DASHBOARD2(:monthOrder, :yearOrder)", nativeQuery = true)
	public Dashboard2 getDashboard2(@Param("monthOrder") String month, @Param("yearOrder") String year);

}
