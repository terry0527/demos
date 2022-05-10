package com.example.bootleetcode.demo;

import java.util.Arrays;

/**
 * 给你两个按非递减顺序排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
 * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n
 *
 */
public class Leet88 {

    public static void main(String[] args) {
        int[] num1 = {1,2,3,0,0,0};
        int[] num2 = {2,5,6};
        int[] result = merge(num1,3,num2,3);
        System.out.println(Arrays.toString(result));
    }

    public static int[] merge(int[] nums1,int m,int[] nums2,int n){
        int k =m+n;
        for(int index = k-1,num1Index = m-1,num2Index = n-1;index >0; index--){
            //num1已经取完，完全取num2的值
            if(num1Index<0){
                nums1[index] = nums2[num2Index--];
            //num2已经取完，完全取num1的值
            }else if(num2Index<0){
                break;
            }else if(nums1[num1Index] > nums2[num2Index]){
                nums1[index]=nums1[num1Index--];
            }else {
                nums1[index]=nums2[num2Index--];
            }
        }
        return nums1;
    }
}
