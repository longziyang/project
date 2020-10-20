package com.project.repository;

import com.project.model.PoetryEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import java.util.List;

public interface PoetryRepository extends ElasticsearchCrudRepository<PoetryEs, Integer> {

    List<PoetryEs> findByContent(String content);

}
