package com.joygin.crm.utils;

/**
 * 获取Service
 */
public class ServiceFactory {
	
	public static Object getService(Object service){
		
		return new TransactionInvocationHandler(service).getProxy();
		
	}
	
}
