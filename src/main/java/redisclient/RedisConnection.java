package redisclient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisConnection {
	
	private static final String REDIS_IP = "127.0.0.1";
	private static final int REDIS_PORT = 6379;
	
	/**
	 * Test the overhead time of 10_000 commands in Batch Seq 
	 */
	private static void batchCommandTest() {
		Jedis jedis = new Jedis(REDIS_IP, REDIS_PORT);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; ++ i) {
			jedis.hset("hashkey:" + i, "field" + i, "value" + i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("10_000 commands in Batch Seq cost time: " + (endTime - startTime));
		jedis.close();
	}
	
	/**
	 * Test the overhead time of 10_000 commands in pipeline 
	 */
	private static void pipeLineCommandTest() {
		Jedis jedis = new Jedis(REDIS_IP, REDIS_PORT);
		long startTime = System.currentTimeMillis();
		for (int i = 0;i < 100; ++ i) {
			Pipeline pipeline = jedis.pipelined();
			for (int j = i * 100; j < (i + 1) * 100; ++ j) {
				pipeline.hset("hashkey:" + i, "field" + i, "value" + i);
			}
			pipeline.syncAndReturnAll();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("10_000 commands in pipeline cost time: " + (endTime - startTime));
		jedis.close();
	}
	
	public static void connectAndClose() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.sadd("daiqing", "haha", "heihei");
		System.out.println(jedis.smembers("daiqing"));
		jedis.close();
	}
	
	public static void main(String[] args) {
		batchCommandTest();
		pipeLineCommandTest();
	}
}
