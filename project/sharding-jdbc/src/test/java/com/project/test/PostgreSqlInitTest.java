package com.project.test;

import com.project.entity.SysDict;
import com.project.mapper.DictMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class) // ÕûºÏ
@Component
public class PostgreSqlInitTest implements ApplicationContextAware {

    @Autowired
    private DictMapper dictMapper;
    private ApplicationContext applicationContext;

    @Test
    public void insert() {

        String[] str = applicationContext.getEnvironment()
                .getActiveProfiles();
        for (String to:str) {
            System.out.println("profiles = "+to);
        }

//        for (int i = 0; i < 20; i++) {
//
//            dictMapper.insertDict("kind" + i, "key" + i, "value" + i, i);
//        }
//
//        List<SysDict> dictList = dictMapper.selectAllDict();
//
//        for (SysDict dict : dictList) {
//
//            System.out.println(dict.toString());
//        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}
