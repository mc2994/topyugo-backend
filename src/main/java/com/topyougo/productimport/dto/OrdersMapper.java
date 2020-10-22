package com.topyougo.productimport.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.topyougo.productimport.model.Orders;

public class OrdersMapper implements RowMapper<Orders> {
	@Override
	public Orders mapRow(ResultSet order, int rowNum) throws SQLException {
		Orders product = new Orders();
		product.setOrderID(order.getLong(1));
		product.setDateOrdered(order.getDate(2));
		product.setFirstName(order.getString(3));
		product.setLastName(order.getString(4));
		product.setOrderNo(order.getString(5));
		product.setOrderStatus(order.getString(6) != null ? OrderStatus.valueOf(order.getString(6)) : OrderStatus.VOIDED);
		product.setCodAmount(order.getDouble(7));
		product.setContactNo(order.getString(8));
		product.setProduct(order.getString(9));
		product.setVariant(order.getString(10));
		product.setAmount(order.getDouble(11));
		product.setQuantity(order.getInt(12));
		product.setOrderAmount(order.getDouble(13));
		product.setAddress(order.getString(14));
		product.setShippingDate(order.getDate(15));
		product.setCourier(order.getString(16)!=null ? Courier.valueOf(order.getString(16)) : Courier.NO_COURIER);
		product.setCustomerNote(order.getString(17));
		product.setShippingDate2(order.getDate(18));
		product.setRtsReason(order.getString(19));
		product.setRtsDetails(order.getString(20));
		product.setDateIntransit(order.getDate(21));
		product.setDaysIntransit(order.getInt(22));
		product.setShippingFee(order.getDouble(23));
		product.setProvince(order.getString(24));
		product.setRegion(order.getString(25));
		product.setTrackingNumber(order.getString(26));
		product.setTrackingStatus(order.getString(27)!=null ? TrackingStatus.valueOf(order.getString(27)) : null);
		return product;
	}
}
