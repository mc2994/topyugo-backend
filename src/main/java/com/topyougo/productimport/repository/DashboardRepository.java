package com.topyougo.productimport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.topyougo.productimport.model.Dashboard1;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard1, String> {

	@Query(value = "CALL DASHBOARD1(:monthOrder, :yearOrder)", nativeQuery = true)
	public Dashboard1 getDashboard1(@Param("monthOrder") String month, @Param("yearOrder") String year);
}
