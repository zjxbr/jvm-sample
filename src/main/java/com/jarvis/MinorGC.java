package com.jarvis;

/**
 * Created by zjx on 15/10/12.
 */
public class MinorGC {

    /**
     * 只触发MinorGC，速度很快，因为是朝
     * @param args
     */
    public static void main(String[] args) {
        long l = 0;
        while (true) {
            String.valueOf(++l);
        }
    }
}
