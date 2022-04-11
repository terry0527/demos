package com.example.utildemo.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {

    /**
     * of()方法与ofNullable()方法的区别：
     *
     * of()方法中不可以传入为null的值，不然会报空指针异常。
     * ofNullable()方法允许传入null值，
     * 如果传入值为null则返回一个Optional.empty(); 所以说当你不确定传入的值是否为空时就使用ofNullable()方法，
     * 如果确定传入的值不为空时就使用of()方法创建Optional容器，有人也许会说直接使用ofNullable()方法不就可以了吗，
     * 其实存在就是合理的，of()方法存在自然有它的意义，当需求是如果为空就不再执行后面的代码时，这个时候就使用of()方法。
     */

    public static void main(String[] args) {
        demo4();
    }

    /**
     * get(), 返回Optional中存储的任意类型的值
     * 如果Optional中的值为null，调用get()方法时则抛出 java.util.NoSuchElementException
     */
    public static void demo(){

        Optional<String> optional = Optional.ofNullable("hello");
        System.out.println("optional = " + optional.get());
        Optional<Integer> optional1 = Optional.ofNullable(100);
        System.out.println("optional1 = " + optional1.get());
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<List<Integer>> optional2 = Optional.ofNullable(list);
        System.out.println("optional2 = " + optional2.get().toString());

        /**
         * optional = hello
         * optional1 = 100
         * optional2 = [1, 2, 3, 4, 5, 6]
         */
    }

    /**
     * 2. ifPresent(Consumer<? super T> consumer)，如果值存在，执行Consumer的具体操作，如果不存，不做任何操作。
     */
    public static void demo1(){

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional.ofNullable(list).ifPresent(System.out::println);

        /**
         * [1, 2, 3, 4, 5, 6]
         */
    }

    /**
     * 3. isPresent(), 如果存在值则返回true，否则返回false；
     */
    public static void demo2(){

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        boolean present = Optional.ofNullable(list).isPresent();
        System.out.println("present = " + present);
        List<Integer> list1 = null;
        boolean present1 = Optional.ofNullable(list1).isPresent();
        System.out.println("present1 = " + present1);

        /**
         *true
         * false
         */
    }

    /**
     * 4. orElse(T other)，如果optional的值存在则返回，否则返回other
     */
    public static void demo3(){
        Optional<String> optional = Optional.ofNullable("hello");
        System.out.println(optional.orElse("value is null"));
        Optional<String> optional2 = Optional.ofNullable(null);
        System.out.println(optional2.orElse("value is null"));

        /**
         * hello
         * value is null
         */
    }

    /**
     * 5. orElseGet(Supplier<? extends T> supplier)，功能与 orElse 类似，区别在于 orElse 可直接返回某个值，
     * orElseGet 需要执行 supplier，并返回其结果
     */
    public static void demo4(){
        Optional<String> optional = Optional.ofNullable("hello");
        System.out.println(optional.orElseGet(() -> "value is null"));
        Optional<String> optional2 = Optional.ofNullable(null);
        System.out.println(optional2.orElseGet(() -> "value is null"));

        /**
         * hello
         * value is null
         */
    }
}
