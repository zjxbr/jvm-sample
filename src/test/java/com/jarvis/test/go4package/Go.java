package com.jarvis.test.go4package;

import org.junit.Test;

/**
 * Created by zjx on 15/10/12.
 */
public class Go {


    private void m(int i) {
        System.out.println(i);
        m(++i);
    }

    /**
     * 线程栈溢出
     */
    @Test
    public void go() {
        m(0);
    }





}
