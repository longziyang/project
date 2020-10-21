//package com.project.config.redis;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.ScanResult;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//public class RedisTest {
//
//	// redis中的四个常用函数setnx getset del get
//
//	public static void main(String[] args) {
//
//		Jedis jedis = new Jedis("127.0.0.1", 6379);
//
//		// 获取所有的锁
//		Set<String> set = jedis.keys("*");
//		System.out.println(set.size());
//		Set<String> setA = jedis.keys("A*");
//		System.out.println(setA.size());
//
//		System.out.println("---------------");
//		jedis.set("asd", "hahaha");
//		System.out.println(jedis.get("asd"));
//		jedis.set("asd", "xjxjij");
//		System.out.println(jedis.get("asd"));
//
//		jedis.set(key, value, nxxx, expx, time);
//		// 使用keys指令可以扫出指定模式的key列表
//		// jedis.keys("Test");
//		// 无阻塞的取出指定keys
//		// ("A?")匹配一个字符
//		ScanResult<String> sr = jedis.scan(100);
//		// jedis.scan("A");
//		List<String> listSr = sr.getResult();
//		for (String string : listSr) {
//
//			System.out.println(string);
//		}
//
//		// // 将给定 key 的值设为 value ，并返回 key 的旧值 (old value)，当 key
//		// // 存在但不是字符串类型时，返回一个错误，当key不存在时，返回null 并且会存进redis
//		// System.out.println(jedis.getSet("lo", "lv"));
//
//		// 获取当前时间戳
//		Long long1 = System.currentTimeMillis();
//		Long long2 = Calendar.getInstance().getTimeInMillis();
//		Long long3 = new Date().getTime();
//
//	}
//
//	public synchronized static void lock1(Jedis jedis, String lock, String value, int timeOut) {
//
//		Long result = jedis.setnx(lock, value);
//		if (result == 1) {
//			jedis.expire(lock, timeOut);
//		}
//	}
//
//	// 锁不具备拥有者标识，即任何客户端都可以解锁
//	public static boolean lock2(Jedis jedis, String lockKey, long expireTime) {
//		String name = Thread.currentThread().getName();
//
//		long expires = System.currentTimeMillis() + expireTime;
//		String expiresStr = String.valueOf(expires);
//
//		// 如果当前锁不存在，返回加锁成功
//		if (jedis.setnx(lockKey, expiresStr) == 1) {
//			System.out.println(name + "加锁成功");
//			return true;
//		}
//
//		// 如果锁存在，获取锁的过期时间
//		// 也可以用getSet方法
//		String currentValueStr = jedis.get(lockKey);
//		System.out.println(name + "当前锁过期时间-" + currentValueStr);
//
//		try {
//			Thread.sleep(1100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
//
//			// 锁已过期，获取上一个锁的过期时间，并设置现在锁的过期时间
//			String oldValueStr = jedis.getSet(lockKey, expiresStr);
//			System.out.println(name + "上一个锁的过期时间为：" + oldValueStr);
//
//			// 当前锁的过期时间和上一个版本的过期时间相同 代表该锁被当前客户端加锁
//			if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
//				// 考虑多线程并发的情况，只有一个线程的设置值和当前值相同，它才有权利加锁
//				return true;
//			}
//		}
//
//		// 其他情况，一律返回加锁失败
//		return false;
//
//	}
//
//	// 会导致任何客户端都可以随时进行解锁，即使这把锁不是它的。
//	public static void unLock1(Jedis jedis, String lockKey) {
//		jedis.del(lockKey);
//	}
//
//	public static void unLock2(Jedis jedis, String lockKey, String requestId) {
//
//		// 判断加锁与解锁是不是同一个客户端
//		if (requestId.equals(jedis.get(lockKey))) {
//			// 若在此时，这把锁突然不是这个客户端的，则会误解锁
//			jedis.del(lockKey);
//		}
//	}
//
//}
