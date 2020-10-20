package com.project.web;

import com.project.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
public class MqController {

    @Autowired
    private MqService mqService;

    @RequestMapping("/transfer")
    @ResponseBody
    public String transfer(int money) {

        String result = mqService.transfer(money);

        return result;
    }

    @RequestMapping("/returnOk")
    @ResponseBody
    public String returnOk(Integer id) {

        return mqService.returnOk(id);

    }

    @Scheduled(fixedDelay = 1000)
    @Async
    public void seek() {

        mqService.seek();

    }

}
