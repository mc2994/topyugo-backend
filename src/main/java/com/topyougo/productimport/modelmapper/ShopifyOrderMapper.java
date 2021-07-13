package com.topyougo.productimport.modelmapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.topyougo.productimport.constant.OrderStatus;
import com.topyougo.productimport.dto.CustomerInfo;
import com.topyougo.productimport.dto.DefaultAddress;
import com.topyougo.productimport.dto.ShopifyLineItems;
import com.topyougo.productimport.dto.ShopifyOrderDTO;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.util.DateUtil;

public class ShopifyOrderMapper {

	public static final Map<String, String> mapValues = new HashedMap<String, String>();

	static {
	   mapValues.put("ODZ", "ODZ");
	   mapValues.put("NO_STOCK", "NO_STOCK");
	   mapValues.put("INVALID_AMOUNT", "INVALID_AMOUNT");
	   mapValues.put("INVALID_ADDRESS", "INVALID_ADDRESS");
	   mapValues.put("INVALID_NO", "INVALID_NO");
	   mapValues.put("SHIPPED", "SHIPPED");
	}

	private ShopifyOrderMapper() {

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
			order.setDateOrdered(DateUtil.formatDate(product.getProcessed_at()));
			
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
	

	
	public static Orders convertShopifyToOrder(ShopifyOrderDTO product) {
		Orders order = new Orders();	
		//order.setOrderID(product.getId());
		order.setOrderAmount(product.getTotal_price());
//		order.setOrderStatus(product.getFinancial_status().equalsIgnoreCase("ON-HOLD") ? OrderStatus.ON_HOLD
//				: OrderStatus.getValueOf(product.getFinancial_status()));
		order.setOrderStatus(OrderStatus.NEW_ORDER);
		order.setOrderNo(product.getName());
		order.setDateOrdered(product.getProcessed_at()!=null ? DateUtil.formatDate(product.getProcessed_at()) : DateUtil.formatDate(product.getCreated_at()));
		
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
}
