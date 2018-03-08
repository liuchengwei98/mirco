package org.integrain.upms.log.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.integrain.upms.localthread.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogUtil {

	@Autowired
	protected HttpServletRequest request;
	
	/**
	 * returnVal 接收的是方法的返回值，返回值中都应该包含操作结果描述信息
	 * @param returnVal
	 */
	public void auditLogAfterReturnString(String returnVal){
		   System.out.println("auditLogAfterReturn string" + returnVal);
		   System.out.println("auditLogAfterReturn requestURI=" + request.getRequestURI());
	   }
	/**
	 * returnVal 接收的是方法的返回值，返回值中都应该包含操作结果描述信息
	 * @param returnVal
	 */
	public void auditLogAfterReturnMap(Map returnVal){
		   System.out.println("auditLogAfterReturn map" + returnVal);
	   }
	/**
	 * e: 接收方法抛出的异常
	 * @param e
	 */
	public void auditLogAfterThrowing(Throwable e){
		   System.out.println("auditLogAfterThrowing void" + e);
	   }
	/**
	 * returnVal 接收的是方法的返回值，返回值中都应该包含操作结果描述信息
	 * @param returnVal
	 */
	public void micrLogAfterReturnString(String returnVal){
		   System.out.println("micrLogAfterReturnString string" + returnVal);
		   System.out.println("micrLogAfterReturnString requestURI=" + request.getRequestURI());
		   System.out.println("micrLogAfterReturnString ContextHolder.getMicrSrvStartTime()=" + ContextHolder.getMicrSrvStartTime());
	   }
	/**
	 * 
	 * @param returnVal
	 */
	public void micrLogBefore(){
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String date = sdf.format(new Date());
		   System.out.println("micrLogBefore date" + date);
		   ContextHolder.setMicrSrvStartTime(date);
		   System.out.println("micrLogBefore ContextHolder.getMicrSrvStartTime()=" + ContextHolder.getMicrSrvStartTime());
	   }
	/**
	 * e: 接收方法抛出的异常
	 * @param e
	 */
	public void micrLogAfterThrowing(Throwable e){
		   System.out.println("micrLogAfterThrowing void" + e);
	   }
}
