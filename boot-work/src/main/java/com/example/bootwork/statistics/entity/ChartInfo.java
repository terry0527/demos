package com.example.bootwork.statistics.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChartInfo {

    private List<String> axis;

    private List series;

}
