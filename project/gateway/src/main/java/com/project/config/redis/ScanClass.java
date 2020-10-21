//package com.project.config.redis;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.ScanParams;
//import redis.clients.jedis.ScanResult;
//import redis.clients.jedis.Tuple;
//
//import java.util.List;
//import java.util.Map;
//
//public class ScanClass {
//
//	// SCAN 命令对应的 Jedis 中的操作如下
//	public static void delLargeListKey(Jedis jedis) {
//		// 游标初始值为0
//		String cursor = ScanParams.SCAN_POINTER_START;
//		String key = "A*";
//		ScanParams scanParams = new ScanParams();
//		scanParams.match(key);// 匹配以 A* 为前缀的 key
//		scanParams.count(1000);// 需要匹配的数量
//		while (!cursor.equals("0")) {
//			// 使用scan命令获取500条数据，使用cursor游标记录位置，下次循环使用
//			ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
//			cursor = scanResult.getStringCursor();// 返回0 说明遍历完成
//			List<String> list = scanResult.getResult();
//			long t1 = System.currentTimeMillis();
//			for (int m = 0; m < list.size(); m++) {
//				String mapentry = list.get(m);
//				// jedis.del(key, mapentry);
//				jedis.ltrim("test:xttblog:", 0, 1);
//			}
//			long t2 = System.currentTimeMillis();
//			System.out.println("删除" + list.size() + "条数据，耗时: " + (t2 - t1) + "毫秒,cursor:" + cursor);
//			if ("0".equals(cursor)) {
//				break;
//			}
//		}
//	}
//
//	// SSCAN 命令对应的 Jedis 中的操作如下
//	public static void delLargeSetKey(Jedis jedis) {
//		// 游标初始值为0
//		String cursor = ScanParams.SCAN_POINTER_START;
//		ScanParams scanParams = new ScanParams();
//		scanParams.count(1000);
//		String key = "test:xttblog";
//		while (true) {
//			// 使用sscan命令获取500条数据，使用cursor游标记录位置，下次循环使用
//			ScanResult<String> sscanResult = jedis.sscan(key, cursor, scanParams);
//			cursor = sscanResult.getStringCursor();// 返回0 说明遍历完成
//			List<String> scanResult = sscanResult.getResult();
//			long t1 = System.currentTimeMillis();
//			for (int m = 0; m < scanResult.size(); m++) {
//				String mapentry = scanResult.get(m);
//				jedis.srem(key, mapentry);
//			}
//			long t2 = System.currentTimeMillis();
//			System.out.println("删除" + scanResult.size() + "条数据，耗时: " + (t2 - t1) + "毫秒,cursor:" + cursor);
//			if ("0".equals(cursor)) {
//				break;
//			}
//		}
//	}
//
//	// HSCAN 命令对应的 Jedis 中的操作如下
//	public static void delLargeHashKey(Jedis jedis) {
//		// 游标初始值为0
//		String cursor = ScanParams.SCAN_POINTER_START;
//		ScanParams scanParams = new ScanParams();
//		scanParams.count(1000);
//		String key = "test:xttblog";
//		while (true) {
//			// 使用hscan命令获取500条数据，使用cursor游标记录位置，下次循环使用
//			ScanResult<Map.Entry<String, String>> hscanResult = jedis.hscan(key, cursor, scanParams);
//			cursor = hscanResult.getStringCursor();// 返回0 说明遍历完成
//			List<Map.Entry<String, String>> scanResult = hscanResult.getResult();
//			long t1 = System.currentTimeMillis();
//			for (int m = 0; m < scanResult.size(); m++) {
//				Map.Entry<String, String> mapentry = scanResult.get(m);
//				jedis.hdel(key, mapentry.getKey());
//			}
//			long t2 = System.currentTimeMillis();
//			System.out.println("删除" + scanResult.size() + "条数据，耗时: " + (t2 - t1) + "毫秒,cursor:" + cursor);
//			if ("0".equals(cursor)) {
//				break;
//			}
//		}
//	}
//
//	// ZSCAN 命令对应的 Jedis 中的操作如下：
//	public static void delLargeZSetKey(Jedis jedis) {
//		// 游标初始值为0
//		String cursor = ScanParams.SCAN_POINTER_START;
//		String key = "test:xttblog:*";
//		ScanParams scanParams = new ScanParams();
//		scanParams.match(key);// 匹配以 test:xttblog:* 为前缀的 key
//		scanParams.count(1000);
//		while (true) {
//			// 使用 zscan 命令获取 500 条数据，使用cursor游标记录位置，下次循环使用
//			ScanResult<Tuple> scanResult = jedis.zscan(key, cursor, scanParams);
//			cursor = scanResult.getStringCursor();// 返回0 说明遍历完成
//			List<Tuple> list = scanResult.getResult();
//			long t1 = System.currentTimeMillis();
//			for (int m = 0; m < list.size(); m++) {
//				Tuple tuple = list.get(m);
//				System.out.println("Element：" + tuple.getElement() + "，Score：" + tuple.getScore());
//			}
//			long t2 = System.currentTimeMillis();
//			System.out.println("删除" + list.size() + "条数据，耗时: " + (t2 - t1) + "毫秒,cursor:" + cursor);
//			if ("0".equals(cursor)) {
//				break;
//			}
//		}
//	}
//
//}
