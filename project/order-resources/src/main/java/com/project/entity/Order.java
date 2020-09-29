package com.project.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_order")
@Data
public class Order {

    @Id
    private Long id;
    private String no;
    private int state;
}
