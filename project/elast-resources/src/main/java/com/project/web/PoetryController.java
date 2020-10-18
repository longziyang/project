//package com.project.web;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.project.entity.Poetry;
//import com.project.model.PoetryEs;
//import com.project.service.PoetryService;
//import tk.mybatis.mapper.entity.Example;
//import tk.mybatis.mapper.entity.Example.Criteria;
//import com.github.pagehelper.PageHelper;
//
//@Controller
//@RequestMapping("/my/Poetry")
//public class PoetryController {
//    @Autowired
//    private PoetryService poetryService;
//
//    @RequestMapping("/insert")
//    @ResponseBody
//    public String insert() {
//        List<Poetry> list = poetryService.selectAll();
//
//        for (Poetry poetry : list) {
//
//            PoetryEs poetryEs = new PoetryEs();
//            BeanUtils.copyProperties(poetry, poetryEs);
//            poetryService.save(poetryEs);
//            System.out.println("存入数据");
//        }
//        System.out.println("ok");
//        return "success";
//    }
//
//    @RequestMapping("byWord")
//    @ResponseBody
//    public List<PoetryEs> selectByWord(String word) {
//
//        System.out.println(word);
//        List<PoetryEs> list = poetryService.selectByContent(word);
//        System.out.println(list.size());
//        return list;
//    }
//
//    // 接受前端传来的值 院系 ，性别，展示到第几行了 ，每个页数固定条数条数
//    public Map<String, Object> getStudentByDepartment(String word, Integer row, Integer account) {
//
//        // 创建查询
//        Example example = new Example(PoetryEs.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andCondition("word=", word);
//
//        // 获取总条数
//        int number = poetryService.selectCountByExample(example);
//        int pageNum = number % account == 0 ? number % account : number % account + 1;
//        System.out.println("pageNum=" + pageNum);
//
//        // 分页每次截取的条数
//        PageHelper.offsetPage((row - 1) * account, account);
//        List<PoetryEs> list = poetryService.selectByExample(example);
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageNum", pageNum);
//        map.put("list", list);
//
//        return map;
//    }
//}