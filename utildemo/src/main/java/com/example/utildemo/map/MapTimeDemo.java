package com.example.utildemo.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * description  map遍历时间花费
 */
public class MapTimeDemo {

    public static void main(String[] args) {
        Map<String, Object> map = getMap();
        keySet(map);
        keySetIterator(map);
        entrySet(map);
        entrySetIterator(map);
        java8(map);
    }

    public static Map getMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        for(int i = 0;i<1000000; i++){
            map.put("A"+i,i);
        }
        return map;
    }

    public static void keySet(Map<String,Object> map){
        long startTime = System.currentTimeMillis();
        int count = 0;
        for(String key : map.keySet()){
            count++;
            String value = map.get(key).toString();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("keySetGetKeyAndValue运行时间" + (endTime - startTime)+"----"+count);
    }

    public static void keySetIterator(Map<String,Object> map){
        long startTime = System.currentTimeMillis();
        Iterator<String> iterator = map.keySet().iterator();
        int count = 0;
        while(iterator.hasNext()){
            count++;
            String key = iterator.next();
            String value = map.get(key).toString();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("keySetIteratorGetKeyAndValue运行时间" + (endTime - startTime)+"---------"+count);
    }

    public static void entrySet(Map<String,Object> map){
        long startTime = System.currentTimeMillis();
        int count = 0;
        for(Map.Entry<String,Object> entry: map.entrySet()){
            count++;
            String key = entry.getKey();
            String value = entry.getValue().toString();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("entrySetGetKeyAndValue运行时间" + (endTime - startTime)+"-----"+count);
    }

    public static void entrySetIterator(Map<String,Object> map){
        long startTime = System.currentTimeMillis();
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            Map.Entry<String,Object> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue().toString();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("entrySetIteratorGetKeyAndValue运行时间" + (endTime - startTime)+"---"+count);
    }

    public static void java8(Map<String,Object> map){
        long startTime =  System.currentTimeMillis();
        map.forEach((key, value) -> {
//            String key1 = key;
//            String value1 = value.toString();
        });
        long endTime = System.currentTimeMillis();
        System.out.println("java8内置方法运行时间" + (endTime - startTime));
    }

}
