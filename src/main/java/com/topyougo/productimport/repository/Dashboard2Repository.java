package com.topyougo.productimport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.topyougo.productimport.model.Dashboard2;

@Repository
public interface Dashboard2Repository extends JpaRepository<Dashboard2, Integer> {
	
	@Query(value = "CALL dashboard2(:monthOrder, :yearOrder)", nativeQuery = true)
	public Dashboard2 getDashboard2(@Param("monthOrder") String month, @Param("yearOrder") String year);
}
