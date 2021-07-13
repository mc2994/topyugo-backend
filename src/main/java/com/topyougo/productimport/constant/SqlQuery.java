package com.topyougo.productimport.constant;

public class SqlQuery {

	public static final String GET_ORDERS = "SELECT * FROM ( SELECT *, 'ADMIN' AS UserType FROM orders UNION ALL SELECT *, 'FINANCE' AS UserType FROM orders WHERE TrackingStatus = 'DELIVERED' AND OrderStatus IN('SHIPPED', 'RRTS', 'RTS', 'RESHIPPED', 'DELIVERED', 'LOST_PARCEL', 'PAID_LOST') UNION ALL SELECT *, 'WAREHOUSE' AS UserType FROM orders WHERE OrderStatus IS NULL OR OrderStatus IN('NEW_ORDER', 'SHIPPED', 'ODZ', 'NO_STOCK', 'INVALID_ADDRESS', 'INVALID_NO', 'INVALID_AMOUNT') UNION ALL SELECT *, 'LOGISTICS' AS UserType FROM orders WHERE\r\n" + 
			"OrderStatus IN('SHIPPED', 'RRTS', 'RTS', 'RESHIPPED', 'DELIVERED', 'LOST_PARCEL', 'PAID_LOST') ) aa WHERE UserType LIKE CONCAT('%',:userType,'%') ";


	public static final String GET_TRACKING_STATUS = "SELECT * FROM orders WHERE Courier='JNT' AND TrackingNumber=:trackingNumber AND TrackingStatus <> 'DELIVERED'";

	public static final String FIND_ALL_COURIER = "SELECT * FROM orders WHERE Courier=:courier AND TrackingNumber <> '' AND TrackingStatus IS NULL order BY ID";
}