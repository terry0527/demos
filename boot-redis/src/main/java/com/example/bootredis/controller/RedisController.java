package com.example.bootredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

/**
 *   只用redis如何实现类似知乎的功能查看最近1000条记录呢?
 */
@RestController
public class RedisController {

    private int maxCount = 5;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping ("/redisTest")
    public String purposePie() {
        redisTemplate.opsForValue().set("name","dadadingdada!");
        System.out.println(redisTemplate.opsForValue().get("name"));
        return " ";
    }

    //https://juejin.cn/post/6877884130567618574
    @GetMapping ("/rank")
    public String rank() {
        //key
        String key = "key1";
        //阈值
        long top  = 10;
        //新访问记录ID
        String value  = "15646051140";
        Double score = redisTemplate.opsForZSet().score(key, value);
        //检索是否有旧记录  1.无则插入记录值  2.有则删除 再次插入
        if(null != score){
            //删除旧的
            redisTemplate.opsForZSet().remove(key,value);
        }
        //加入新的记录，设置当前时间戳为分数score
        redisTemplate.opsForZSet().add(key,value,System.currentTimeMillis());
        //获取总记录数
        Long aLong = redisTemplate.opsForZSet().zCard(key);
        if(aLong > top){
            //获取阈值200之后的记录  (0,1] 并移除
            redisTemplate.opsForZSet().removeRange(key,0,aLong-top-1);
        }
        return  null;
    }


    @GetMapping ("/getTopList")
    public String getList() {
        String key = "key1";
        long start = 1;
        long size  = 10;
        Set<ZSetOperations.TypedTuple> scoreWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(key, start - 1, size - 1 );
        Iterator<ZSetOperations.TypedTuple> iterator = scoreWithScores.iterator();
        BigDecimal bigDecimal = null;
        while (iterator.hasNext()){
            ZSetOperations.TypedTuple next = iterator.next();
            bigDecimal = BigDecimal.valueOf(next.getScore());
            System.out.println("==》ID： "+next.getValue()+" 时间： "+bigDecimal.toPlainString());
        }
        return null;
    }

}
