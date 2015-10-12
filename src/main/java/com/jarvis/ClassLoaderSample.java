package com.jarvis;

/**
 * Created by zjx on 15/10/1.
 */
public class ClassLoaderSample {

    public static final int USELESS = 1;

    static {
        System.err.println(ClassLoaderSample.class.getName() + " loaded.");
    }

    public int method1(){
       return 1;
    }

}
