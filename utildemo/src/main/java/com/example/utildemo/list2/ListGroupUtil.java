package com.example.utildemo.list2;

import java.util.ArrayList;
import java.util.List;

/**
 * description 按指定大小，分隔集合，将集合按规定个数分为n个部分
 * history:
 */
public class ListGroupUtil {

    public static List<List<String>> splitList(List<String> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<String>> result = new ArrayList<List<String>>();

        int size = list.size();
        int count = (size + len - 1) / len;

        for (int i = 0; i < count; i++) {
            List<String> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        List<List<String>> li=splitList(list,3);
        System.out.println("***"+li);
        for(List obj:li){
            String value = "";
            for(Object ao:obj){
                value=value+ao.toString()+",";
            }
            String real=value.substring(0,value.length()-1);
            System.out.println("value***"+real);
            System.out.println("+++++");
        }

    }
}
