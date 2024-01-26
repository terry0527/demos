package com.example.bootwork.statistics;

import cn.hutool.json.JSONUtil;
import com.example.bootwork.entity.Param;
import com.example.bootwork.entity.Statistic1VO;
import com.example.bootwork.util.DatePadd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaddstatisticsDemo {

    public static void main(String[] args) throws Exception{

        padd();
    }

    public static void padd() throws Exception{

        Param param = new Param();

        List<Statistic1VO> list = new ArrayList<>();

        Statistic1VO bean2 = new Statistic1VO();
        bean2.setDate(LocalDate.now().minusDays(6));
        bean2.setNum(10L);
        list.add(bean2);

        Statistic1VO bean = new Statistic1VO();
        bean.setDate(LocalDate.now());
        bean.setNum(3L);
        list.add(bean);

        try {
            list = new DatePadd<Statistic1VO>(){
                @Override
                protected Statistic1VO paddStrategy(){
                    return new Statistic1VO();
                }
            }.paddLocalDate(param.getStartDate(),param.getEndDate(),list,Statistic1VO.class,"date");
        } catch (Exception e) {
            e.printStackTrace();
        }
       System.out.println(JSONUtil.toJsonStr(list));
    }
}
