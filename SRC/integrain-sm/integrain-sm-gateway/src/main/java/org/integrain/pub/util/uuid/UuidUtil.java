package org.integrain.pub.util.uuid;

import java.util.UUID;
/**
 * 唯一标识符工具类
 * @author zhangqingquan
 *
 */
public class UuidUtil {

	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
