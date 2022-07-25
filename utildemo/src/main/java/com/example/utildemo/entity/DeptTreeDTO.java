package com.example.utildemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DeptTreeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long pId;

    private String name;

    private String type;

    private String pname;

    private Integer sort;

    private Long score;

    private List<DeptTreeDTO> children;
}
