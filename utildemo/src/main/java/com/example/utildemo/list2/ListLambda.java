package com.example.utildemo.list2;

import com.example.utildemo.entity.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description  Lambda lsit的使用
 * history:
 */
public class ListLambda {

    public static void main(String[] args) {
        List<Person> personList = getList1();
        List<String> list2 = getList2();

        List<Person> dcvList = personList.stream().filter((item) ->
                ("13".equals(item.getAge()))).collect(Collectors.toList());
        System.out.println(personList.get(1).getAge());

        // lambda写法
        //forEach()方法
        //两种输出方式
        personList.forEach(s -> {
            System.out.println("使用lambda表达式实现：" + s.getName());
        });

        personList.forEach(System.out::println);

        personList.forEach(s -> {
            if (s.getAge().equals("13")) {
                System.out.println("使用lambda表达式实现：" + s.getAge());
            }
        });

        //removeIf()
        // 使用lambda表达式实现 删除长度大于3的元素
        list2.removeIf(str -> str.length()>2);
        list2.forEach(System.out::println);

        //replaceAll()
        // 使用Lambda表达式实现
        list2.replaceAll(s -> {
            if (s.length() > 2) {
                return s.toUpperCase();
            } else {
                return s;
            }
        });
        list2.forEach(System.out::println);

        //sort()
        // 现在可以直接使用List.sort()方法，结合Lambda表达式实现对list元素排序
        list2.sort((str1, str2) -> str1.length() - str2.length());
        // 还可以进一步简化成如下进行排序
        list2.sort(Comparator.comparingInt(String::length));
        // 输出排序后的结果
        list2.forEach(System.out::println);
        list2.forEach(s -> {
            System.out.println("sort()表达式实现：" + s);
        });
    }

    public static List<Person> getList1(){
        List<Person> personList = Arrays.asList(
             new Person("Tom", 8900, 23, "male", "New York"),
             new Person("Jack", 7000, 25, "male", "Washington"),
             new Person("Lily", 7800, 21, "female", "Washington"),
             new Person("Anni", 8200, 24, "female", "New York")
        );
        return personList;
    }

    public static List<String> getList2(){
        List<String> list2 = new ArrayList<>(Arrays.asList("are", "you", "ok"));
        return list2;
    }
}
