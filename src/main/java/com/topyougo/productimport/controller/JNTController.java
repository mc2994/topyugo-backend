package com.topyougo.productimport.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.topyougo.productimport.model.JNTAccounts;
import com.topyougo.productimport.repository.JNTRepository;

@RestController
@RequestMapping("/api/jnt")
public class JNTController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JNTRepository jntRepository;
	
	@RequestMapping(value="/getTrackingStatus", method= RequestMethod.GET)
	public ResponseEntity<?> getTrackingStatus(){
		JNTAccounts jntUser = jntRepository.getOne(1);
		String jntUrl = "http://203.177.126.110:22225/feilvbin-vip-interface/api/getOrders.do";
		Parameter param = new Parameter();
		param.setCusCode("");
		param.setDelegateStatus(-1);
		param.setEndDate(convertDate());
		param.setExpressType(-1);
//		StringBuilder ids = new StringBuilder();
//		//ids.append("\'\"");
//		ids.append("'"+"2007210003195797"+"'");
//		//ids.append("\\'");
		param.setIds("'"+"2007210003195797"+"'");
		param.setOrderType(2);
		param.setPage(1);
		param.setPageSize(20);
		param.setPayType(-1);
		param.setStartDate(convertDate());
		param.setStatusType("-1");
		param.setType(2);
		
		RequestPayLoad payload = new RequestPayLoad();
		payload.setParameter(param);
		payload.setS("2174196cdfaaee87647a9a5b760a298d");
		
		Date date = new Date();  
        Timestamp ts=new Timestamp(date.getTime());  
        
		payload.setT(ts.getTime());
		payload.setToken("session_4a0ba8944d80f2c2");
		
		System.out.println(toJson(payload));
		ResponseEntity<String> response = restTemplate.postForEntity(jntUrl, payload, String.class);
		System.out.println(">>>>>>>>>>>> "+response.getBody());
		return new ResponseEntity<String>("sdadadad", HttpStatus.OK);		
	}
	
	public static String convertDate() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		return date;
	}
	
	public static String toJson(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(obj);
	}
}

class Parameter{
		private String cusCode = "";
		private Integer delegateStatus= -1;
		private String endDate;
		private Integer expressType= -1;
		private String ids;
		private Integer orderType;
		private Integer page;
		private Integer pageSize;
		private Integer payType;
		private String startDate;
		private String statusType;
		private Integer type;
		
		Parameter(){
			
		}

		public String getCusCode() {
			return cusCode;
		}

		public void setCusCode(String cusCode) {
			this.cusCode = cusCode;
		}

		public Integer getDelegateStatus() {
			return delegateStatus;
		}

		public void setDelegateStatus(Integer delegateStatus) {
			this.delegateStatus = delegateStatus;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public Integer getExpressType() {
			return expressType;
		}

		public void setExpressType(Integer expressType) {
			this.expressType = expressType;
		}

		public String getIds() {
			return ids;
		}

		public void setIds(String ids) {
			this.ids = ids;
		}

		public Integer getOrderType() {
			return orderType;
		}

		public void setOrderType(Integer orderType) {
			this.orderType = orderType;
		}

		public Integer getPage() {
			return page;
		}

		public void setPage(Integer page) {
			this.page = page;
		}

		public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

		public Integer getPayType() {
			return payType;
		}

		public void setPayType(Integer payType) {
			this.payType = payType;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getStatusType() {
			return statusType;
		}

		public void setStatusType(String statusType) {
			this.statusType = statusType;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return "Parameter [cusCode=" + cusCode + ", delegateStatus=" + delegateStatus + ", endDate=" + endDate
					+ ", expressType=" + expressType + ", ids=" + ids + ", orderType=" + orderType + ", page=" + page
					+ ", pageSize=" + pageSize + ", payType=" + payType + ", startDate=" + startDate + ", statusType="
					+ statusType + ", type=" + type + "]";
		}
}

class RequestPayLoad{
	
	private Parameter parameter;
	private String s;
	private Long t;
	private String token;
	
	public Parameter getParameter() {
		return parameter;
	}
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public Long getT() {
		return t;
	}
	public void setT(Long t) {
		this.t = t;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "RequestPayLoad [parameter=" + parameter + ", s=" + s + ", t=" + t + ", token=" + token + "]";
	}
}
