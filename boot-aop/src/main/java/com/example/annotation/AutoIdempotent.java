package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现接口幂等性注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIdempotent {

    long expireTime() default 10000;

    /**
     * 1.最基本的注解定义
     *
     * public @interface MyAnnotation{
     * 	public String name();
     * 	int age();
     * 	String sex() default "女"；
     * }
     * 在自定义注解中，其实现部分只能定义注解类型元素!
     * 2.常用的元注解
     * 元注解：专门修饰注解的注解
     * @Target
     * @Target是专门用来限定某个自定义注解（如上面的@interface MyAnnotation）能够被应用在哪些Java元素上面的。枚举类型。
     * 因此，我们可以在使用@Target时指定注解的使用范围
     *
     * //@MyAnnotation被限定只能使用在（类、接口）TYPE或（方法）METHOD上面
     * @Target(value = {ElementType.METHOD,ElementType.TYPE})
     * public @interface MyAnnotation {
     *     public String name();
     *     int age();
     *     String sex() default "女";
     * }
     * @Retention注解，用来修饰自定义注解的生命力。
     * a.如果一个注解被定义为RetentionPolicy.SOURCE，则它将被限定在Java源文件中，那么这个注解即不会参与编译也不会在运行期起任何作用，这个注解就和一个注释是一样的效果，只能被阅读Java文件的人看到；
     * 　　b.如果一个注解被定义为RetentionPolicy.CLASS，则它将被编译到Class文件中，那么编译器可以在编译时根据注解做一些处理动作，但是运行时JVM（Java虚拟机）会忽略它，我们在运行期也不能读取到，是默认的；
     * 　　c.如果一个注解被定义为RetentionPolicy.RUNTIME，那么这个注解可以在运行期的加载阶段被加载到Class对象中。那么在程序运行阶段，我们可以通过反射得到这个注解，并通过判断是否有这个注解或这个注解中属性的值，从而执行不同的程序代码段。我们实际开发中的自定义注解几乎都是使用的RetentionPolicy.RUNTIME。
     *
     * 使用此注解修饰自定义注解生命力的示例如下：
     *
     * //设置注解的生命力在运行期
     * @Retention(RetentionPolicy.RUNTIME)
     * public @interface MyAnnotation {
     *     public String name();
     *     int age();
     *     String sex() default "女";
     * }
     * 原文链接：https://blog.csdn.net/lemo_ice/article/details/124058542
     */
}
