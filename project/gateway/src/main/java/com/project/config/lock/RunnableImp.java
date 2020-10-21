package com.project.config.lock;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class RunnableImp implements Runnable {

	private RedisTemplate<String,Object> redisTemplate;
	private String lockKey;
	private static final Long RELEASE_SUCCESS = 1L;
	// private static Lock lock = new ReentrantLock();
	private Long id;

	public RunnableImp( String lockKey) {
		this.lockKey = lockKey;
		this.id = Thread.currentThread().getId();
	}

	static Integer shop = 100;

	@Override
	public void run() {

		while (shop > 0) {

			lock();
			if (shop > 0) {
				shop--;
				System.out.println(Thread.currentThread().getName() + "--" + shop);
			}
			unlock();

		}

	}

	public void lock() {

		boolean result= redisTemplate.opsForValue().setIfAbsent(lockKey, id.toString(),600, TimeUnit.SECONDS);

		if (result) {
			System.out.println(Thread.currentThread().getId() + "加锁成功!");
		}

	}

	public void unlock() {

//		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//		Object result = redisTemplate.eval(script, Collections.singletonList(lockKey),
//				Collections.singletonList(id.toString()));

		if ((RELEASE_SUCCESS).equals(1)) {
			redisTemplate.delete(lockKey);
		}
	}

}
