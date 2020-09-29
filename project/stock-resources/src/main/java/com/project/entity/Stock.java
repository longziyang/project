package com.project.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_stock")
@Data
public class Stock {

    @Id
    private Long id;
    private String name;
    private Integer inventory;
    private int state;
}
