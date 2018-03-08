package org.integrain.common.redis.controller;


import javax.servlet.http.HttpServletRequest;

import org.integrain.common.redis.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: 
 * @author: 
 * @date: 2017-11-12
 *
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {
	//private final static Logger logger = LoggerFactory.getLogger(RedisController.class);
	
	@Autowired
	protected HttpServletRequest request;
   
	@RequestMapping(value = "/sethash")
	public long setHash(@RequestParam(value = "key", required = true)  String key, 
			@RequestParam(value = "field", required = true)  String field,
			@RequestParam(value = "value", required = true)  String value) {
       long l = RedisClient.setHash(key, field, value);
       return l;
	}
	@RequestMapping(value = "/gethash")
	public String getHash(@RequestParam(value = "key", required = true)  String key, 
			@RequestParam(value = "field", required = true)  String field) {
       String str = RedisClient.getHash(key, field);
       return str;
	}
	@RequestMapping(value = "/delhash")
	public long delHash(@RequestParam(value = "key", required = true)  String key, 
			@RequestParam(value = "field", required = true)  String field) {
		long l = RedisClient.delHash(key, field);
       return l;
	}
	@RequestMapping(value = "/setstr")
	public String setStr(@RequestParam(value = "key", required = true)  String key, 
			@RequestParam(value = "value", required = true)  String value) {
		String str = RedisClient.setStr(key, value);
		return str;
	}
	@RequestMapping(value = "/getstr")
	public String getStr(@RequestParam(value = "key", required = true)  String key) {
       String str = RedisClient.getStr(key);
       return str;
	}
	@RequestMapping(value = "/delstr")
	public long delStr(@RequestParam(value = "key", required = true)  String key) {
		long l = RedisClient.delStr(key);
		return l;
	}
}
