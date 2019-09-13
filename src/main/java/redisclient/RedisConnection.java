package redisclient;

import redis.clients.jedis.Jedis;

public class RedisConnection {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.sadd("daiqing", "haha", "heihei");
		System.out.println(jedis.smembers("daiqing"));
	}
}
