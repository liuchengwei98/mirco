package org.integrain.upms.localthread;

/**
 * 线程公共类
 * @author zhangqingquan
 *
 */
public class ContextHolder {

	//微服务开始时间
	private static ThreadLocal<String> micrSrvStartTime = new ThreadLocal<String>();
	
	public static String getMicrSrvStartTime() {
		return micrSrvStartTime.get();
	}

	public static void setMicrSrvStartTime(String micrSrvStartTime) {
		ContextHolder.micrSrvStartTime.set(micrSrvStartTime);
	}
	
}
