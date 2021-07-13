package com.topyougo.productimport.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.topyougo.productimport.model.Dashboard5;

@Repository
public interface Dashboard5Repository extends JpaRepository<Dashboard5, String> {
	
	@Query(value = "CALL DASHBOARD5(:monthOrder, :yearOrder)", nativeQuery = true)
	public List<Dashboard5> getDashboard5(@Param("monthOrder") String month, @Param("yearOrder") String year);

}
