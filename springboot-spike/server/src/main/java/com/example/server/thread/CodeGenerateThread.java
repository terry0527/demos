package com.example.server.thread;


import com.example.model.entity.RandomCode;
import com.example.model.mapper.RandomCodeMapper;
import com.example.server.utils.RandomUtil;

/**
 *
 *
 **/
public class CodeGenerateThread implements Runnable{

    private RandomCodeMapper randomCodeMapper;

    public CodeGenerateThread(RandomCodeMapper randomCodeMapper) {
        this.randomCodeMapper = randomCodeMapper;
    }

    @Override
    public void run() {
        RandomCode entity=new RandomCode();
        entity.setCode(RandomUtil.generateOrderCode());
        randomCodeMapper.insertSelective(entity);
    }
}