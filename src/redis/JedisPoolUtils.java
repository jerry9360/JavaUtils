package redis;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



/**
 * redis连接
 * 
 * @author wxb
 * 
 */
public class JedisPoolUtils {

	private static final Logger logger = LoggerFactory.getLogger(JedisPoolUtils.class);

	private static JedisPoolUtils jedisPoolUtils;// 单例

	private JedisPool pool;// redis连接

	private JedisPoolUtils() {
		logger.info("始初始化redis连接...");
		initialJedisPool();
		logger.info("初始化redis连接池...");
	}
	
	
	/**
	 * 获得单例
	 * @return
	 */
	public static JedisPoolUtils getInstance() {
		if (jedisPoolUtils == null) {
			synchronized(JedisPoolUtils.class) {
				if(jedisPoolUtils == null) {
					jedisPoolUtils = new JedisPoolUtils();
				}
			}
		}
		return jedisPoolUtils;
	}

	public void initialJedisPool() {
		if (pool == null) {
			// 建立连接池配置参数?
			JedisPoolConfig config = new JedisPoolConfig();
			// 设置最大连接数
			config.setMaxActive(50);
			// 设置最大阻塞时间，记住是毫秒数milliseconds
			config.setMaxWait(10000l);
			// 设置空间连接
			config.setMaxIdle(10);
			// 创建连接   10000 第四个参数time out，设置成我们能容忍的超时时间，单位是毫秒
			pool = new JedisPool(config, ConfigUtil.getValue("redis_ip"), 6379, 10000);
		}
	}


	/**
	 * 获取�?个jedis 对象
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		try{
			return pool.getResource();
		}catch(Exception e) {
			logger.info("getJedis error",e);
		}
		return null;
	}
	
	 /**
     * 归还链接?
     * 
     * @param jedis
     */
    public void returnRes(Jedis jedis) {
    	try{
    		pool.returnResource(jedis);
    	}catch(Exception e) {
			logger.info("returnRes error",e);
		}
    }
    
    public static void main(String[] args) {
    	Jedis jedis = JedisPoolUtils.getInstance().getJedis();
    	Set<String> set = jedis.keys("Taglib:Corp:*");
    	for(String s:set){
    		System.out.println(s);
    		jedis.del(s);
    	}
    	JedisPoolUtils.getInstance().returnRes(jedis);
    	System.out.println(set.size());
	}

}
