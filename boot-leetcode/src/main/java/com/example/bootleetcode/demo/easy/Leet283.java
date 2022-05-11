package com.example.bootleetcode.demo.easy;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 */
public class Leet283 {
    public static void main(String[] args) {
        int[] nums = {0,1,0,4,0,0};
        int[] result = merge(nums);
        System.out.println(Arrays.toString(result));
    }

    public static int[] merge(int[] nums){
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j= 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完了，剩下的都是0
        //第二次遍历把末尾的元素都赋值0
        for(int i=j;i<nums.length;i++){
            nums[i]=0;
        }
        return nums;
    }
}
