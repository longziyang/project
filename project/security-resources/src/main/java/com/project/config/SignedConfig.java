package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lzy
 * @version 创建时间：2020年8月9日 下午9:55:04
 * @ClassName 类名称
 * @Description 类描述
 */
@Configuration // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class SignedConfig {

    @Autowired
    private RedisTemplate redisTemplate;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    private static String yesterday;
    private static String today;
    //private static final Date DATE = ;
    public static String todayKey = "";

    static {

        today = format.format(new Date());
        todayKey = "today" + today;
    }

    /**
     * 日期以及用户id作为k 存储签到功能
     *
     * @return
     */
    @Scheduled(cron = "00 00 00 * * ?")
    // 或直接指定时间间隔，例如：5秒
    // @Scheduled(fixedRate=5000)
    private void setToday() {

        todayKey = "today" + today;
        redisTemplate.delete(yesterday);
        yesterday = today;
        Date date = new Date();
        today = format.format(date);
        //保留一个昨天的时间信息
        redisTemplate.opsForValue().set("yesterday" + yesterday, yesterday);
        redisTemplate.opsForValue().set(todayKey, today);

    }

}
