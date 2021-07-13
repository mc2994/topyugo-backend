package com.topyougo.productimport.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.topyougo.productimport.dto.OrderDTO;
import com.topyougo.productimport.dto.ShopifyOrderDTO;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.model.ShopifyStores;
import com.topyougo.productimport.modelmapper.ShopifyOrderMapper;
import com.topyougo.productimport.repository.OrderRepository;
import com.topyougo.productimport.repository.ShopifyStoreRepository;
import com.topyougo.productimport.util.JsonUtil;

@RestController
@RequestMapping("/api/store")
public class ShopifyIntegrationController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate restTemplate;	
	
	@Autowired
	private ShopifyStoreRepository shopifyRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@PostMapping("/shopifyWebhook")
	public void shopifyWebhook(@RequestHeader(value = "X-Shopify-Shop-Domain", required = true) String storeName,
			@RequestBody ShopifyOrderDTO response){
		
		System.out.println(JsonUtil.toJson(response));
		
		ShopifyStores store = shopifyRepository.findShopifyStoresByStoreName(storeName);
		
		if(ObjectUtils.isEmpty(store)) {
			return;
		}
		Orders order = ShopifyOrderMapper.convertShopifyToOrder(response);
		orderRepository.save(order);	
	}
	
	@GetMapping("/orders")
	public ResponseEntity<?> addOrder(){		
		List<ShopifyStores> store = shopifyRepository.findAll();
		StringBuilder storeUrl = new StringBuilder();
		List<ShopifyOrderDTO> ordered = new ArrayList<ShopifyOrderDTO>();	
		
		for(ShopifyStores config : store) {
			storeUrl.append(config.getApiKey());
			storeUrl.append(config.getPassword());
			storeUrl.append(config.getDomainName());
			storeUrl.append("orders.json?status=any&limit=250&fields=id,number,processed_at,customer,name,financial_status,total_price,line_items");
			
			OrderDTO records = new OrderDTO();
			records.setOrders(new ArrayList<ShopifyOrderDTO>());
				
			Long lastNumber = 0l;
			System.out.println("Store "+config.getStoreName());
			while(records.getOrders()!=null) {			
				String url = storeUrl.toString();
				String params = url+"&since_id="+lastNumber;
				System.out.println("URL "+params);
				records.setOrders(getOrdersPaginated(config, params));
				if(records.getOrders()!=null) {
					for(ShopifyOrderDTO dto : records.getOrders()) {
						lastNumber = dto.getId();
					}
					ordered.addAll(records.getOrders());
				}
			}
			
			if(ordered!=null) {
				List<Orders> orderedList = ShopifyOrderMapper.convertShopifyOrders(ordered);
				orderRepository.saveAll(orderedList);	
			}
				
			storeUrl.setLength(0);
		}
				
		return new ResponseEntity<List<ShopifyOrderDTO>>(ordered, HttpStatus.OK);		
	}	
	
	
	private List<ShopifyOrderDTO> getOrdersPaginated(ShopifyStores store, String storeUrl){
		HttpHeaders headers = new HttpHeaders();		
	    headers.set("X-Shopify-Access-Token", store.getPassword());
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	    
	    HttpEntity<OrderDTO> entity = new HttpEntity<OrderDTO>(headers);	    
	    ParameterizedTypeReference<OrderDTO> listOfStrings = new ParameterizedTypeReference<OrderDTO>() {};
		ResponseEntity<OrderDTO> orders = restTemplate.exchange(storeUrl, HttpMethod.GET, entity, listOfStrings);
		OrderDTO response = orders.getBody();
		
		if(response.getOrders().size()<1) {
			response.setOrders(null);
		}
		return response.getOrders();
	}
}