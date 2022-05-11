package com.example.bootleetcode.demo.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * 思路
 * 原始数组：[4,3,2,7,8,2,3,1]
 * 重置后为：[-4,-3,-2,-7,8,2,-3,-1]
 * 【笔记】将所有正数作为数组下标，置对应数组值为相反数。那么，仍为正数的位置即为（未出现过）消失的数字。
 */
public class Leet448 {

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        List<Integer> result = merge(nums);
        System.out.println(result);
    }

    public static List<Integer> merge(int[] nums){

        int n = nums.length;
        for(int num:nums){
            //对n取模还原出它本来的值
            int x = (num-1)%n;
            nums[x]+= n;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(nums[i]<=n){
                result.add(i+1);
            }
        }
        return result ;
    }
}
