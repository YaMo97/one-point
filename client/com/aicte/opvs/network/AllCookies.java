package com.aicte.opvs.network;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;

public class AllCookies {
	
	public static CookieManager cookieManager;
	public static CookieStore cookieStore;
	
	static {
		cookieManager = new CookieManager();
	    CookieHandler.setDefault(cookieManager);
    	cookieStore = cookieManager.getCookieStore();
	}
}
