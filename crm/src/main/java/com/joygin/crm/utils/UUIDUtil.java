package com.joygin.crm.utils;

import java.util.UUID;

/**
 * 获取Id
 */
public class UUIDUtil {
	
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replaceAll("-","");
		
	}
	
}
