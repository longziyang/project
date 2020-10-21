package com.project.config.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestClass {

	@Test
	public void test() {

		Jedis jedis = new Jedis();
		
		RedisImpClass redisImpClass = new RedisImpClass(jedis, "lock", 1000);

		Thread t1 = new Thread(redisImpClass);
		Thread t2 = new Thread(redisImpClass);
		Thread t3 = new Thread(redisImpClass);
		Thread t4 = new Thread(redisImpClass);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}

}
