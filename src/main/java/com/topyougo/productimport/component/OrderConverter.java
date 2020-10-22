package com.topyougo.productimport.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.topyougo.productimport.dto.Courier;
import com.topyougo.productimport.dto.CustomerInfo;
import com.topyougo.productimport.dto.DefaultAddress;
import com.topyougo.productimport.dto.OrderStatus;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.dto.ShopifyLineItems;
import com.topyougo.productimport.dto.ShopifyOrderDTO;
import com.topyougo.productimport.dto.TrackingStatus;
import com.topyougo.productimport.model.Orders;

public class OrderConverter {

	public static final Map<String, String> mapValues = new HashedMap<String, String>();

	static {
	   mapValues.put("ODZ", "ODZ");
	   mapValues.put("NO_STOCK", "NO_STOCK");
	   mapValues.put("INVALID_AMOUNT", "INVALID_AMOUNT");
	   mapValues.put("INVALID_ADDRESS", "INVALID_ADDRESS");
	   mapValues.put("INVALID_NO", "INVALID_NO");
	   mapValues.put("SHIPPED", "SHIPPED");
	}

	private OrderConverter() {

	}

	public static List<ProductsDTO> convertModelToDTO(List<Orders> orderList) {
		List<ProductsDTO> productList = new ArrayList<ProductsDTO>();
		orderList.forEach(order -> {
			ProductsDTO product = new ProductsDTO();
			product.setOrderID(order.getOrderID());
			product.setDateOrdered(formatDate(order.getDateOrdered()));
			product.setFirstName(order.getFirstName());
			product.setLastName(order.getLastName());
			product.setOrderNo(order.getOrderNo());
			product.setOrderStatus(order.getOrderStatus()!=null ? order.getOrderStatus().getValue() : "");
			product.setCodAmount(order.getCodAmount());
			product.setContactNo(order.getContactNo());
			product.setProduct(order.getProduct());
			product.setVariant(order.getVariant());
			product.setAmount(order.getAmount());
			product.setQuantity(order.getQuantity());
			product.setOrderAmount(order.getOrderAmount());
			product.setAddress(order.getAddress());
			product.setShippingDate(formatDate(order.getShippingDate()));
			product.setCourier(order.getCourier()!=null ? order.getCourier().getValue() : "");
			product.setCustomerNote(order.getCustomerNote());
			//product.setShippingDate2(order.getShippingDate2());
			product.setRtsReason(order.getRtsReason());
			product.setRtsDetails(order.getRtsDetails());
			product.setDateIntransit(formatDate(order.getDateIntransit()));
			product.setDaysIntransit(order.getDaysIntransit());
			product.setShippingFee(order.getShippingFee());
			product.setProvince(order.getProvince());
			product.setRegion(order.getRegion());
			product.setTrackingNumber(order.getTrackingNumber());
			product.setTrackingStatus(order.getTrackingStatus()!=null ? order.getTrackingStatus().getValue() : "");
//			if(order.getTrackingStatus()!=null) {
//				product.setTrackingStatus(order.getTrackingStatus().getValue());
//			}else {
//				product.setTrackingStatus(null);
//			}
			productList.add(product);
		});
		return productList;

	}

	public static List<Orders> convertDTOToModel(List<ProductsDTO> productList) {
		List<Orders> ordersList = new ArrayList<Orders>();
		productList.forEach(product -> {
			Orders order = new Orders();
			order.setOrderID(product.getOrderID());
			order.setDateOrdered(formatDate(product.getDateOrdered()));
			order.setFirstName(product.getFirstName());
			order.setLastName(product.getLastName());
			order.setOrderNo(product.getOrderNo());
			order.setOrderStatus(OrderStatus.valueOf(product.getOrderStatus()));
			order.setCodAmount(product.getCodAmount());
			order.setContactNo(product.getContactNo());
			order.setProduct(product.getProduct());
			order.setVariant(product.getVariant());
			order.setAmount(product.getAmount());
			order.setQuantity(product.getQuantity());
			order.setOrderAmount(product.getOrderAmount());
			order.setAddress(product.getAddress());
			order.setShippingDate(formatDate(product.getShippingDate()));
			if(!product.getCourier().equals("")) {
				order.setCourier(Courier.valueOf(product.getCourier()));
			}else {
				order.setCourier(null);
			}
			order.setCustomerNote(product.getCustomerNote());
			//order.setShippingDate2(formatDate(product.getShippingDate2()));
			order.setRtsReason(product.getRtsReason());
			order.setRtsDetails(product.getRtsDetails());
			order.setDateIntransit(product.getDateIntransit());
			order.setDaysIntransit(product.getDaysIntransit());
			order.setShippingFee(product.getShippingFee());
			order.setProvince(product.getProvince());
			order.setRegion(product.getRegion());
			order.setTrackingNumber(product.getTrackingNumber());
			if(!product.getTrackingStatus().equals("")) {
				order.setTrackingStatus(TrackingStatus.valueOf(product.getTrackingStatus()));
			}else {
				order.setTrackingStatus(null);
			}
			ordersList.add(order);
		});
		return ordersList;
	}
	
	public static Courier checkValueOf(String courierValue) {
		if(courierValue.equals(Courier.JNT.getValue())) {			
			return Courier.JNT;
		}
		if(courierValue.equals(Courier.LBC.getValue())) {			
			return Courier.LBC;
		}	
		return Courier.JRS;
	}
	
	public static Orders convertProductToOrders(ProductsDTO product) {
			Orders order = new Orders();
			order.setOrderID(product.getOrderID());
			order.setDateOrdered(formatDate(product.getDateOrdered()));
			order.setFirstName(product.getFirstName());
			order.setLastName(product.getLastName());
			order.setOrderNo(product.getOrderNo());
			if(product.getOrderStatus().equalsIgnoreCase("NEW_ORDER")
					&& !product.getTrackingNumber().isEmpty() ) {					
				order.setOrderStatus(OrderStatus.SHIPPED);
			}else {
				order.setOrderStatus(OrderStatus.valueOf(product.getOrderStatus()));
			}	
			order.setCodAmount(product.getCodAmount());
			order.setContactNo(product.getContactNo());
			order.setProduct(product.getProduct());
			order.setVariant(product.getVariant());
			order.setAmount(product.getAmount());
			order.setQuantity(product.getQuantity());
			order.setOrderAmount(product.getOrderAmount());
			order.setAddress(product.getAddress());
			order.setShippingDate(formatDate(product.getShippingDate()));
			order.setCourier(checkValueOf(product.getCourier()));
			order.setCustomerNote(product.getCustomerNote());
		//	order.setShippingDate2(formatDate(product.getShippingDate2()));
			order.setRtsReason(product.getRtsReason());
			order.setRtsDetails(product.getRtsDetails());
			order.setDateIntransit(formatDate(product.getDateIntransit()));
			order.setDaysIntransit(product.getDaysIntransit());
			order.setShippingFee(product.getShippingFee());
			order.setProvince(product.getProvince());
			order.setRegion(product.getRegion());
			order.setTrackingNumber(product.getTrackingNumber());
			order.setTrackingStatus(TrackingStatus.valueOf(product.getTrackingStatus()));
		return order;
	}
	
	public static List<Orders> convertShopifyOrders(List<ShopifyOrderDTO> shopifyOrderList) {
		List<Orders> ordersList = new ArrayList<Orders>();
		shopifyOrderList.forEach(product -> {
			Orders order = new Orders();	
			//order.setOrderID(product.getId());
			order.setOrderAmount(product.getTotal_price());
//			order.setOrderStatus(product.getFinancial_status().equalsIgnoreCase("ON-HOLD") ? OrderStatus.ON_HOLD
//					: OrderStatus.getValueOf(product.getFinancial_status()));
			order.setOrderNo(product.getName());
			order.setDateOrdered(formatDate(product.getProcessed_at()));
			
			CustomerInfo customer = product.getCustomer();
			
			order.setFirstName(customer.getFirst_name()!=null ? customer.getFirst_name() : "");
			order.setLastName(customer.getLast_name()!=null ? customer.getLast_name() : "");
			order.setContactNo(customer.getPhone()!=null ? customer.getPhone() : "");	
			
			DefaultAddress address = customer.getDefault_address()!=null ? customer.getDefault_address() : new DefaultAddress();			
			order.setAddress(address.getAddress1()!=null ? address.getAddress1() : "");
			
			for(ShopifyLineItems item : product.getLine_items()) {
				order.setProduct(item.getTitle());
				order.setQuantity(item.getQuantity());
				order.setVariant(item.getVariant_title());
				order.setCodAmount(item.getPrice());				
			}
			ordersList.add(order);
		});
		return ordersList;
	}
	
	public static Date formatDate(Date processedAt) {
		   SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		 //SimpleDateFormat dt = new SimpleDateFormat("MMM dd, yyyy");
	        String date = null;
	        Date finalDate = null;
	        if(processedAt!=null) {
	        try {
	        	date = dt.format(processedAt);
	        	finalDate = dt.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        }
			return finalDate;
	}
	
	public static Orders convertShopifyToOrder(ShopifyOrderDTO product) {
		Orders order = new Orders();	
		//order.setOrderID(product.getId());
		order.setOrderAmount(product.getTotal_price());
//		order.setOrderStatus(product.getFinancial_status().equalsIgnoreCase("ON-HOLD") ? OrderStatus.ON_HOLD
//				: OrderStatus.getValueOf(product.getFinancial_status()));
		order.setOrderStatus(OrderStatus.NEW_ORDER);
		order.setOrderNo(product.getName());
		order.setDateOrdered(product.getProcessed_at()!=null ? formatDate(product.getProcessed_at()) : formatDate(product.getCreated_at()));
		
		CustomerInfo customer = product.getCustomer();
		
		order.setFirstName(customer.getFirst_name()!=null ? customer.getFirst_name() : "");
		order.setLastName(customer.getLast_name()!=null ? customer.getLast_name() : "");
		order.setContactNo(customer.getPhone()!=null ? customer.getPhone() : "");	
		
		DefaultAddress address = customer.getDefault_address()!=null ? customer.getDefault_address() : new DefaultAddress();			
		order.setAddress(address.getAddress1()!=null ? address.getAddress1() : "");
		
		for(ShopifyLineItems item : product.getLine_items()) {
			order.setProduct(item.getTitle());
			order.setQuantity(item.getQuantity());
			order.setVariant(item.getVariant_title());
			order.setCodAmount(item.getPrice());				
		}
		return order;
	}
	
	public static ProductsDTO convertOrderToProduct(Orders order) {
		ProductsDTO product = new ProductsDTO();
		product.setOrderID(order.getOrderID());
		product.setDateOrdered(formatDate(order.getDateOrdered()));
		product.setFirstName(order.getFirstName());
		product.setLastName(order.getLastName());
		product.setOrderNo(order.getOrderNo());
		
				
		if(order.getOrderStatus()!=null) {
			if(order.getOrderStatus().getValue().equals("ON-HOLD")) {
				product.setOrderStatus(OrderStatus.ON_HOLD.getValue());
			} else { 
				product.setOrderStatus(order.getOrderStatus().getValue());
			}	
		}else {
			product.setOrderStatus(OrderStatus.VOIDED.getValue());
		}
		product.setCodAmount(order.getCodAmount());
		product.setContactNo(order.getContactNo());
		product.setProduct(order.getProduct());
		product.setVariant(order.getVariant());
		product.setAmount(order.getAmount());
		product.setQuantity(order.getQuantity());
		product.setOrderAmount(order.getOrderAmount());
		product.setAddress(order.getAddress());
		product.setShippingDate(formatDate(order.getShippingDate()));
		product.setCourier(order.getCourier()!=null ? order.getCourier().getValue() : "");
		product.setCustomerNote(order.getCustomerNote());
		//product.setShippingDate2(formatDate(order.getShippingDate2()));
		product.setRtsReason(order.getRtsReason());
		product.setRtsDetails(order.getRtsDetails());
		product.setDateIntransit(formatDate(order.getDateIntransit()));
		product.setDaysIntransit(order.getDaysIntransit());
		product.setShippingFee(order.getShippingFee());
		product.setProvince(order.getProvince());
		product.setRegion(order.getRegion());
		product.setTrackingNumber(order.getTrackingNumber());
		product.setTrackingStatus(order.getTrackingStatus()!=null ? order.getTrackingStatus().getValue() : "");
	return product;
}
	
	public static List<Orders> convertDTOTrackingToModel(List<ProductsDTO> productList) {
		List<Orders> ordersList = new ArrayList<Orders>();
		productList.forEach(product -> {
			Orders order = new Orders();
			order.setOrderNo(product.getOrderNo());
			order.setCourier(checkValueOf(product.getCourier()));
			order.setTrackingNumber(product.getTrackingNumber());
			ordersList.add(order);
		});
		return ordersList;
	}
}
