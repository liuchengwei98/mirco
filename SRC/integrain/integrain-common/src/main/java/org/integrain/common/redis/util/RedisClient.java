package org.integrain.common.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 客户端操作工具类
 * @author zhangqingquan
 *
 */
public class RedisClient {

	//Redis服务器IP
    private static String ADDR = "127.0.0.1";
    
    //Redis的端口号
    private static int PORT = 6379;
    
    //访问密码,若你的redis服务器没有设置密码，就不需要用密码去连接
    private static String AUTH = "123456";
    
    //可用连接实例的最大数目，默认值为8；
    private static int MAX_TOTAL = 512;
    
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 50;
    
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
    private static int MAX_WAIT = 10000;
    
    private static int TIMEOUT = 10000;
    
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    
    private static JedisPool jedisPool = null;
    
    /**
     * 初始化Redis连接池
     */
    static {
        try {
        	JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                return jedis;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
    /**
     * 添加一个字符串缓存
     * @param key
     * @param value
     * @return OK
     */
    public static String setStr(String key, String value){
    	return getJedis().set(key, value);
    }
    /**
     * 取字符串缓存
     * @param key
     * @return
     */
    public static String getStr(String key){
    	return getJedis().get(key);
    }
    /**
     * 删除字符串缓存
     * @param key
     * @return
     */
    public static Long delStr(String key){
    	return getJedis().del(key);
    }
    /**
     * 向Hash缓存中添加一个元素
     * @param key
     * @param field
     * @param value
     * @return 返回添加的条数
     */
    public static Long setHash(String key,String field, String value){
    	return getJedis().hset(key, field, value);
    }
    /**
     * 从Hash缓存中获取一个元素
     * @param key
     * @param field
     * @return 元素值
     */
    public static String getHash(String key,String field){
    	return getJedis().hget(key, field);
    }
    /**
     * 从Hash缓存中删除一个元素
     * @param key
     * @param field
     * @return 删除的条数
     */
    public static Long delHash(String key,String field){
    	return getJedis().hdel(key, field);
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*getJedis().set("xiaomin", "1212233");
        System.out.println(getJedis().get("xiaomin1"));
        System.out.println(getJedis().get("xiaomin"));*/
		/*Map map = new HashMap();
		map.put("userId", "ZQQ");
		map.put("userName", "张青全");
		Map map2 = new HashMap();
		map2.put("userId", "ZQQ2");
		map2.put("userName", "张青全2");
        getJedis().hset("USERINFO", "ZQQ", JSONObject.fromObject(map).toString());
        getJedis().hset("USERINFO", "ZQQ2", JSONObject.fromObject(map2).toString());*/
        /*String value = getJedis().hget("USERINFO", "ZQQ");
        System.out.println(value);
        Map getMap = (Map)JSONObject.toBean(JSONObject.fromObject(value), Map.class);
        System.out.println(getMap.get("userId"));
        System.out.println(getMap.get("userName"));
		Map map3 = new HashMap();
		map3.put("userId", "ZQQ4");
		map3.put("userName", "张青全4");
		System.out.println(getJedis().hset("USERINFO", "ZQQ3", JSONObject.fromObject(map3).toString()));*/
		System.out.println(1111);
		System.out.println(getJedis().set("xiaomin111", "1212233"));
		System.out.println(2222);
		
	}

}
