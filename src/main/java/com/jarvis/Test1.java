package com.jarvis;

/**
 * Created by zjx on 15/10/11.
 */
public class Test1 {

    private String name="test1";


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Test1{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
