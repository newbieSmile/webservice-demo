package com.example.demo.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.LoggerFactory;

import com.example.demo.service.UserService;

import ch.qos.logback.classic.Logger;

//webservice客户端 该类提供两种不同方式来调用webservice服务(代理工厂方式、动态调用)
public class CxfClient {
	 private static final Logger log = (Logger) LoggerFactory.getLogger(CxfClient.class);
	public static void main(String[] args) {
		log.info("开始 main2...");
		CxfClient.main2();
	}

	// 1.代理类工厂的方式,需要拿到对方的接口地址
	public static void main1() {
		try {
			String address = "http://127.0.0.1:8080/soap/userService?wsdl";
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			jaxWsProxyFactoryBean.setAddress(address);
			jaxWsProxyFactoryBean.setServiceClass(UserService.class);
			UserService us = (UserService) jaxWsProxyFactoryBean.create();
			String userId = "test3";
			String result = us.getUserName(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main2() {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		 Client client = dcf.createClient("http://127.0.0.1:8080/soap/userService?wsdl");
		Object[] objects = new Object[0];
		try {
			objects = client.invoke("getUserName", "9999999");
			System.out.println("objects[0]:" + objects[0]);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}
}