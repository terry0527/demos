package com.example.springboot8.list;

import com.example.springboot8.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * stream()优点
 *
 * 无存储。stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
 * 为函数式编程而生。对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新stream。
 * 惰式执行。stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
 * 可消费性。stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。
 */
public class LambdaDemo {

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
        result.forEach(value -> System.out.print("list.stream().filter{}"+value));
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

        List<String> list6 = getList2();
        //removeIf()
        // 使用lambda表达式实现 删除长度大于3的元素
        list6.removeIf(str -> str.length()>2);
        list6.forEach(System.out::println);

        //replaceAll()
        // 使用Lambda表达式实现
        list6.replaceAll(s -> {
            if (s.length() > 2) {
                return s.toUpperCase();
            } else {
                return s;
            }
        });
        list6.forEach(System.out::println);

        //sort()
        // 现在可以直接使用List.sort()方法，结合Lambda表达式实现对list元素排序
        list6.sort((str1, str2) -> str1.length() - str2.length());
        // 还可以进一步简化成如下进行排序
        list6.sort(Comparator.comparingInt(String::length));
        // 输出排序后的结果
        list6.forEach(System.out::println);
        list6.forEach(s -> {
            System.out.println("sort()表达式实现：" + s);
        });
    }

    public static List<Integer> getList(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        return list;
    }

    public static List<String> getList2(){
        List<String> list2 = new ArrayList<>(Arrays.asList("are", "you", "ok"));
        return list2;
    }

        public static void demo() {
            //使用Stream中的of方法传入两个字符串
            Stream.of("2L:小王:18","1L:小杨:20")
                    .map(new Function<String, User>() {
                        /*调用Stream中的map方法，使用匿名接口Function，
                        需要重写Function中的抽象方法apply，apply方法需要传入两个数据，
                        前一个为转化前的String类型，后一个为转化后的对象类型*/
                        @Override
                        //传入要转变的参数
                        public User apply(String s) {
                            String[] str = s.split(":");
                            //调用String中的split方法，以：切割，定义一个字符串接收切割后的字符串数据
                            User people = new User(Long.parseLong(str[0]),str[1],Integer.valueOf(str[2]));//对象家接收匿名对象切割后的元素。数组索引0为字符串，数组索引1为数字
                            //返回people类型对象
                            return people;
                        }
                    }).forEach(people-> System.out.println("people = " + people));
            //使用Stream中的forEach遍历People中的对象，使用了Lambda方式，重写了方法遍历输出
        }


}
