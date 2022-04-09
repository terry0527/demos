package com.example.utildemo.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


/**
 * description  Lambda  的使用
 * history:
 */
public class LambdaTest {


    /**
     *       方法名                         简介
     *   collect(toList())               通过 Stream 生成一个列表
     *       map                         将流中的一个值转换成一个新的值
     *      filter                       过滤 Stream 中的元素
     *     flatMap                       将多个 Stream 连接成一个 Stream
     *      max                          求最大值
     *      min                          求最小值
     *      reduce                       从一组值中生成一个新的值
     */

    public static void main(String[] args) {
        //collect(toList()) & filter
        //先创建了一个 List 对象并初始化，然后筛选出大于 2 的值，输出。
        //filter 方法的作用是过滤 Stream 中的元素，filter 方法是一个高阶函数，接收一个函数接口作为参数，
        // 此高阶函数返回一个 boolean 值，返回 true 的元素会保留下来；
        //collect(toList()) 方法将 filter 操作返回的 Stream 生成一个 List。
        //高阶函数：接收或返回一个函数接口的函数称为高阶函数。
        //函数接口：只包含一个函数的接口成为函数接口。
        List<Integer> list = getList();
        List<Integer> result = list.stream().filter((value) -> value > 2).collect(toList());

        //两种输出方法
        result.forEach((value) -> System.out.print("list.stream().filter{}"+value));
        result.forEach(System.out::println);

        //map
        //map 函数的作用是将流中的一个值转换成一个新的值，举个例子，我们要将一个 List 转换成 List ，
        // 那么就可以使用 map 方法
        List<Integer> list2 = getList();
        List<String> result2 = list2.stream().map(value -> String.format("String:%s", value)).collect(toList());
        result2.forEach((value) -> {System.out.println("map{}."+value);});

        //flatMap：将多个 Stream 连接成一个 Stream，这个怎么理解呢，举个例子：
        //首先定义一个 List 对象，将这个对象中的每一个 String 都分割成一个字母并生成一个新的 List 对象，代码：
        List<String> list3 = Arrays.asList("abc", "def", "ghi");
        List<Character> result3 = list3.stream().flatMap(value -> {
            char[] chars = value.toCharArray();
            Character[] characters = new Character[chars.length];
            for(int i = 0; i < characters.length; i++){
                characters[i] = chars[i];
            }
            return Stream.of(characters);
        }).collect(toList());
        result3.forEach((value) -> {System.out.println("flatMap{}."+value);});

        //max&min
        //求最大值最小值
        List<Integer> list4 = Arrays.asList(0, 1, 2, 3);
        Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
        System.out.println(list4.stream().min(comparator).get());
        System.out.println(list4.stream().max(comparator).get());

        //从一组值中生成一个新的值，reduce 函数其实用途非常广泛，作用也比较大，我们举一个累加的例子：
        //reduce 函数的一个参数为循环的初始值，这里计算累加时初始值为 0，acc 代表已经计算的结果，item 表示循环的每个元素。
        List<Integer> list5 = Arrays.asList(0, 1, 2, 3);
        int count = list5.stream().reduce(0, (acc, item) -> acc + item).intValue();
        System.out.println("list5{}"+count);

    }

    public static List<Integer> getList(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        return list;
    }
}
