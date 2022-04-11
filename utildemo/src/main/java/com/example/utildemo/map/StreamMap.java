package com.example.utildemo.map;

import com.example.utildemo.entity.Person;
import com.example.utildemo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMap {

    // Collectors.toMap()，一般用于将一个List转换为Map。常见用法：

    public static List<User> getList() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1L, "张三", 18));
        userList.add(new User(2L, "李四", 19));
        userList.add(new User(3L, "王五", 18));
        return userList;
    }

    //两个参数的用法
    public static void demo1(){
       List<User> userList = getList();
        //将userList转化为key为id，value为User对象的map
        Map<Long, User> map = userList.stream().collect(Collectors.toMap(User::getId, p -> p));

        /**
         * Map<Long, User> map = userList.stream().collect(Collectors.toMap(User::getId, p -> p));
         * 这一步就是将userList 转换为key为id，value为User对象的map。
         *
         * User::getId ===》 User对象的getId方法
         * p -> p ===》就是进来的是什么，最终就是什么，这里就是进来的是User对象，出去的也就是User对象
         *
         * 而这时map里的（模拟）值是：
         * {
         * 	1: User(1, "张三", 18)
         * 	2: User(2, "李四", 19)
         * 	3: User(3, "王五", 18)
         * }
         *
         * 还可以换一下：
         *
         * Map<Long, String> map = userList.stream().collect(Collectors.toMap(User::getId, User::getName));
         * 1
         * 这个获取的就是key为id，value为name的map了。
         * ————————————————
         * 原文链接：https://blog.csdn.net/qq_37253891/article/details/108488919
         */
    }

    //三个参数的用法
    public static void demo2(){
        /**
         * 还是沿用上面那个例子，如果这个时候你想获取key是age，value是name的map呢？如果你还是沿用上面的方法，就会出问题了，
         * 因为有两个age是 18 的数据，也就是存在重复的key，会直接报错，想不报错的话，就可以利用第三个参数了。
         */
        List<User> userList = getList();

        Map<Integer, String> map = userList.stream().collect(Collectors.toMap(User::getAge, User::getName, (a, b) -> b));

        /**
         * (a, b) -> b的意思就是，如果存在重复的，永远取后面一个
         *
         * 这时，map里的值就是：
         *
         * {
         * 	18: "王五"
         * 	19: "李四"
         * }
         *
         */
    }

    //三个参数的用法
    public static void demo3(){
        /**
         * 还是沿用上面那个例子，如果这个时候你想获取key是age，value是name的map呢？如果你还是沿用上面的方法，就会出问题了，
         * 因为有两个age是 18 的数据，也就是存在重复的key，会直接报错，想不报错的话，就可以利用第三个参数了。
         */
        List<User> userList = getList();

        Map<Integer, String> map = userList.stream().collect(Collectors.toMap(User::getAge, User::getName, (a, b) -> b));

        /**
         * (a, b) -> b的意思就是，如果存在重复的，永远取后面一个
         *
         * 这时，map里的值就是：
         *
         * {
         * 	18: "王五"
         * 	19: "李四"
         * }
         *
         */
    }

    // Collectors.groupingBy()
    public static void demo4(){
        /**
         * 还是沿用上面那个例子。当你想获取key是age的map，又不想覆盖掉重复项数据，这个时候就可以用 Collectors.groupingBy 了。
         */
        List<User> userList = getList();

        Map<Integer, List<User>> map = userList.stream().collect(Collectors.groupingBy(User::getAge));

        /**
         * 可以看到，这次的返回值变成了 Map<Integer, List> 了，也就是说，变成了key是age，value是User对象的集合了。
         * 这时，map里的值就变成了：
         *
         * {
         * 	18: [User(1, "张三", 18), User(3, "王五", 18)]
         * 	19: [User(2, "李四", 19)]
         * }
         *
         *
         */
    }

}
