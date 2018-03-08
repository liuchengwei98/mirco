package org.integrain.upms.client.authentication.context;

import org.jasig.cas.client.util.AssertionHolder;

public class BspInfo {
	
	public static String getUserId() {
		String userId = "";
		try {
			userId = AssertionHolder.getAssertion().getPrincipal().getName();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userId;
	}
	
}
