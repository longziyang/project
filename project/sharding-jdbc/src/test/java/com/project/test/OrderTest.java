package com.project.test;

import com.project.entity.Dict;
import com.project.entity.Order;
import com.project.entity.User;
import com.project.mapper.DictMapper;
import com.project.mapper.OrderMapper;

import com.project.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class) // 整合
public class OrderTest {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserDb(){

        for (int i = 0; i < 20; i++) {

            userMapper.insert("name"+1,'A');
        }
        List<User> orderList = userMapper.selectAll();
        System.out.println(Arrays.toString(orderList.toArray()));

    }
    @Test
    public void testInsert() throws Exception {


//        for (int i = 0; i < 20; i++) {
//
//            dictMapper.insert();
//        }
        List<Dict> dictList = dictMapper.selectAll();
        System.out.println(Arrays.toString(dictList.toArray()));
//        for (int i = 0; i < 20; i++) {
//
//            String name = "商品名称编号" + (i + 1);
//            orderMapper.insert((i + 0.1 * i), name, (long) i);
//        }
//        List<Order> orderList = orderMapper.selectAll();
//        System.out.println(Arrays.toString(orderList.toArray()));
    }
}
