package com.project.web;

import java.util.List;
import com.project.model.PoetryEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.service.PoetryService;


@Controller
@RequestMapping("/poetry")
public class PoetryController {
    @Autowired
    private PoetryService poetryService;

    @RequestMapping("/init")
    @ResponseBody
    public String init() {

        return poetryService.init();
    }

    @RequestMapping("byWord")
    @ResponseBody
    public List<PoetryEs> limit(String content, Integer row, Integer pageNum) {

        return poetryService.limit(content,row,pageNum);
    }
}