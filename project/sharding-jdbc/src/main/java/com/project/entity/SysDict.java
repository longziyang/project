package com.project.entity;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "t_sys_dict")
public class SysDict {

    private Long id;
    private String dictKind;
    private String dictKey;
    private String dictValue;
    private int disOrder;
}
