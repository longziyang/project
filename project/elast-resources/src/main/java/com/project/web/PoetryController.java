package com.project.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.entity.Poetry;
import com.project.model.PoetryEs;
import com.project.service.PoetryService;

@Controller
@RequestMapping("/my/Poetry")
public class PoetryController {
    @Autowired
    private PoetryService poetryService;

    @RequestMapping("/insert")
    @ResponseBody
    public String insert() {
        List<Poetry> list = poetryService.selectAll();

        for (Poetry poetry : list) {

            PoetryEs poetryEs = new PoetryEs();
            BeanUtils.copyProperties(poetry, poetryEs);
            poetryService.save(poetryEs);
            System.out.println("存入数据");
        }
        System.out.println("ok");
        return "success";
    }

    @RequestMapping("byWord")
    @ResponseBody
    public List<PoetryEs> selectByWord(String word) {

        System.out.println(word);
        List<PoetryEs> list = poetryService.selectByContent(word);
        System.out.println(list.size());
        return list;
    }
}