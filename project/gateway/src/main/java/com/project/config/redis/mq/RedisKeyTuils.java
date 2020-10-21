package com.project.config.redis.mq;

public class RedisKeyTuils {

	private static String EVENT_KEY = "ASYNC_EVENT_KEY";

	public static String getEventQueueKey() {
		return EVENT_KEY;
	}

}
