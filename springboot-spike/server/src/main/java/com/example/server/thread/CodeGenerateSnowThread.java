package com.example.server.thread;

import com.example.model.entity.RandomCode;
import com.example.model.mapper.RandomCodeMapper;
import com.example.server.utils.SnowFlake;

/**
 *
 *
 **/
public class CodeGenerateSnowThread implements Runnable{

    private static final SnowFlake SNOW_FLAKE=new SnowFlake(2,3);

    private RandomCodeMapper randomCodeMapper;

    public CodeGenerateSnowThread(RandomCodeMapper randomCodeMapper) {
        this.randomCodeMapper = randomCodeMapper;
    }

    @Override
    public void run() {
        RandomCode entity=new RandomCode();
        entity.setCode(String.valueOf(SNOW_FLAKE.nextId()));
        randomCodeMapper.insertSelective(entity);
    }
}