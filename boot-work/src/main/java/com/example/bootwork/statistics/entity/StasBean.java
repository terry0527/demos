package com.example.bootwork.statistics.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class StasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer value;

}
