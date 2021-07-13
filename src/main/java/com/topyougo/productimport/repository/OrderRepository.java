package com.topyougo.productimport.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.topyougo.productimport.constant.SqlQuery;
import com.topyougo.productimport.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

	Orders findOrdersByOrderNoAndProduct(String orderNo, String product);

	Orders findOrdersByOrderNo(String orderNo);

	Orders findOrdersByTrackingNumber(String orderNo);

	List<Orders> findAllByOrderByOrderIDDesc();

	List<Orders> findOrdersByDateOrdered(Date orderDate);

	@Query(nativeQuery = true, value = SqlQuery.FIND_ALL_COURIER)
	List<Orders> findAllByCourierOrderByIDDesc(@Param("courier") String courier);
	
//	Orders saveOrder(Orders order);
//	
//	Orders updateOrder(Orders order);
}
