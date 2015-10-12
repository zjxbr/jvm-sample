package com.jarvis.test.go1leijiazai;

import org.junit.Test;

/**
 * Created by zjx on 15/10/10.
 */
public class Go {

    /**
     * 直接定义该类的类才会被初始化（在该jvm）
     */
    @Test
    public void go() {
        // 会打印出什么？
        System.out.println(SubClass.value);

    }

    /**
     * 不会加载SbuClass</br>
     * 因为在编译阶段，已经把HI常量存到Go 本身的常量池中，在执行中，并不会再引用SubClass的 HI 常量了
     */
    @Test
    public void go3() {
        System.out.println(SubClass.HI);
    }

    /**
     * 不会被初始化，记载的是 [Lcom.jarvis.test.lesson1.SuperClass 对象</br>
     * 数组对象是由虚拟机自动生成的，直接继承与Object类
     */
    @Test
    public void go2() {

        // 会打印出什么?
        SuperClass[] array = new SuperClass[10];
        System.out.println(array.getClass());
    }


}
