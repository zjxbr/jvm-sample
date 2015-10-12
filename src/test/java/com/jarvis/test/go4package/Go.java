package com.jarvis.test.go4package;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zjx on 15/10/12.
 */
public class Go {


    private void m(int i) {

        System.out.println(i);
        m(++i);
    }

    /**
     * 线程栈溢出</br>
     * 可以-Xss2m 设置线程栈大小为2M，太大了，默认是512k或1m</br>
     * 问题1：反复执行这个方法，打印出来会获得相同的结果吗？
     * 问题2：如果希望打印出来的值变小应该怎么做?
     */
    @Test
    public void go0() {
        try {
            m(0);
            fail();
        } catch (StackOverflowError e) {

        }
    }


    /**
     * 堆内存溢出
     */
    @Test
    public void go1() {

        long cnt = 0l;
        List<String> l = new LinkedList<String>();
        try {
            while (true) {
                l.add("haha");
                cnt++;
                if(cnt == Long.MAX_VALUE){
                    break;
                }
            }
            fail();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }


    }


}
