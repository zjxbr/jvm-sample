package com.jarvis;

/**
 * Created by zjx on 15/10/12.
 */
public class MajorGC {

    private static int _100M = 1024*1024*100;

    /**
     * 分配了100M空间，直接进入老年代，触发了 Major GC
     * @param args
     */
    public static void main(String[] args){

        while (true){
           byte[] bs = new byte[_100M];
        }

    }
}
