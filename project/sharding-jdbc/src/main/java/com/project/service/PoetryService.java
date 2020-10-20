package com.project.service;

import com.project.entity.Poetry;
import com.project.mapper.PoetryMapper;
import com.project.model.PoetryEs;
import com.project.repository.PoetryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoetryService {
    @Autowired
    private PoetryMapper poetryMapper;
    @Autowired
    private PoetryRepository poetryRepository;

    public String init() {
        List<Poetry> list = poetryMapper.selectAll();

        for (Poetry poetry : list) {

            PoetryEs poetryEs = new PoetryEs();
            BeanUtils.copyProperties(poetry, poetryEs);
            poetryRepository.save(poetryEs);
        }

        return "success";
    }

    public List<PoetryEs> limit(String content, Integer row, Integer pageNum) {

        return poetryRepository.findByContent(content);

    }
}
