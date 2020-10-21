package com.project.config.redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisImpClass implements Runnable {

	private static final Long RELEASE_SUCCESS = 1L;
	// private static final String LOCK_SUCCESS = "OK";
	// private static final String SET_IF_NOT_EXIST = "NX";
	// private static final String SET_WITH_EXPIRE_TIME = "PX";
	static Integer n = 1;
	Jedis jedis;
	String lock;
	long time;

	public RedisImpClass(Jedis jedis, String lock, long time) {

		this.jedis = jedis;
		this.lock = lock;
		this.time = time;

	}

	public static boolean lock(Jedis jedis, String lock, String requestId, long expireTime) {

		// 第三个为nxxx，这个参数我们填的是NX，意思是SET IF NOT
		// EXIST，当key不存在时，进行set操作；若key存在，则不做任何操作；
		// 第四个为expx，PX，给key加一个过期的设置，第五个参数决定。
		// 第五个为time，与第四个参数相呼应，代表key的过期时间。
		String var1 = jedis.set(lock, requestId, "NX", "EX", expireTime);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "OK".equals(var1);
	}

	/**
	 * 释放分布式锁
	 * 
	 * @param lockKey锁
	 * @param requestId 请求标识
	 * @return 是否释放成功
	 */
	public static boolean unLock(Jedis jedis, String lockKey, String value) {

		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
		Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(value));

		if (RELEASE_SUCCESS.equals(result)) {

			System.out.println(Thread.currentThread().getName() + "解锁");
			return true;
		}
		return false;

	}

	@Override
	public void run() {

		while (n > 0) {

			synchronized (this) {

				if (n > 0) {
					lock(jedis, lock, n.toString(), time);
					n--;
					System.out.println(Thread.currentThread().getName() + "获取锁--卖出商品  还剩" + n);
					unLock(jedis, lock, jedis.get(lock));
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("库存不足");
				}

			}

		}

	}
}
