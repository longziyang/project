package com.project.service;

import com.project.entity.Poetry;
import com.project.mapper.PoetryMapper;
import com.project.model.PoetryEs;
import com.project.repository.PoetryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PoetryService {
    @Autowired
    private PoetryMapper poetryMapper;
    @Autowired
    private PoetryRepository poetryRepository;

    public PoetryMapper getPoetryMapper() {
        return poetryMapper;
    }

    public List<Poetry> selectAll() {
        return poetryMapper.selectAll();
    }

    public void save(PoetryEs poetryEs) {
        poetryRepository.save(poetryEs);
    }

    public List<PoetryEs> selectByContent(String content) {

        return poetryRepository.findByContent(content);
    }

}
