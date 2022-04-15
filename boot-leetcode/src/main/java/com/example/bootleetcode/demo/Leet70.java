package com.example.bootleetcode.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class Leet70 {
    public static void main(String[] args) {
        climbStairs(3);
    }

    private static Map<Integer,Integer> storMap = new HashMap<>();

    public static int climbStairs(int n){
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        if(null!=storMap.get(n)){
            return storMap.get(n);
        }else {
            int result = climbStairs(n-1)+climbStairs(n-2);
            storMap.put(n,result);
            System.out.println(result);
            return result;
        }
    }

    //循环的解法，自底上累加

    public static int climbStairs2(int n){
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        int result = 0;
        int pre = 2;
        int prePrev= 1;
        for (int i = 3; i <=n; i++) {
            result = pre +prePrev;
            prePrev = pre;
            pre = result;
        }
        System.out.println("climbStairs2"+result);
        return result;
    }
}
