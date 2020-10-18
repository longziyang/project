package com.dog.redis;

import java.util.Collections;
import redis.clients.jedis.Jedis;

public class RedisTest {

	private static final String LOCKED_SUCCESS = "OK";
	private static final String NX = "NX";
	private static final String EXPIRE_TIME = "PX";
	private static final Long RELEASE_SUCCESS = 1L;

	// 加锁
	public static boolean lock(Jedis jedis, String lockKey, String uniqueId, long expireTime) {

		String result = jedis.set(lockKey, uniqueId, NX, EXPIRE_TIME, expireTime);
		return LOCKED_SUCCESS.equals(result);

	}

	// 脚本解锁
	public static boolean unLock(Jedis jedis, String lockKey, String uniqueId) {
		String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
		Object result = jedis.eval(luaScript, Collections.singletonList(lockKey), Collections.singletonList(uniqueId));
		return RELEASE_SUCCESS.equals(result);

	}

	// 获取上一个值 与当前值对比
	public static void releaseLock(Jedis jedis, String lockKey, String uniqueId) {

		jedis.getSet(lockKey, uniqueId);
		if (uniqueId.equals(jedis.get(lockKey))) {
			jedis.del(lockKey);
		}
	}

}
