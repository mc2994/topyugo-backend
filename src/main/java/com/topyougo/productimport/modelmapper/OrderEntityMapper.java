package com.topyougo.productimport.modelmapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.topyougo.productimport.constant.Courier;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.model.Orders;

public class OrderEntityMapper {

	private static ModelMapper modelMapper = new ModelMapper();

	public static Orders convertProductToOrders(ProductsDTO product) {
		return modelMapper.map(product, Orders.class);
	}

	public static ProductsDTO convertOrderToProduct(Orders order) {
		return modelMapper.map(order, ProductsDTO.class);
	}

	public static List<ProductsDTO> convertModelToDTO(List<Orders> orderList) {
		List<ProductsDTO> productList = new ArrayList<ProductsDTO>();
		orderList.forEach(order -> {
			ProductsDTO product = convertOrderToProduct(order);
			productList.add(product);
		});
		return productList;

	}

	public static List<Orders> convertDTOToModel(List<ProductsDTO> productList) {
		List<Orders> ordersList = new ArrayList<Orders>();
		productList.forEach(product -> {
			Orders order = convertProductToOrders(product);
			ordersList.add(order);
		});
		return ordersList;
	}

	private static Courier checkValueOf(String courierValue) {
		if (courierValue.equals(Courier.JNT.getValue())) {
			return Courier.JNT;
		}
		if (courierValue.equals(Courier.LBC.getValue())) {
			return Courier.LBC;
		}
		return Courier.JRS;
	}
}