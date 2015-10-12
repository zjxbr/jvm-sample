package com.jarvis.test.go2classloader;

import com.jarvis.NetworkClassLoader;
import org.junit.Test;

/**
 * Created by zjx on 15/10/11.
 */
public class Go {


    /**
     * 双亲委派模型 - AppClassLoader
     */
    @Test
    public void go0() {

        ClassLoader loader =
                Go.class.getClassLoader();
        System.out.println(loader);
    }


    /**
     * 双亲委派模型 - ExtClassLoader
     */
    @Test
    public void go1() {
        ClassLoader loader =
                Go.class.getClassLoader();
        ClassLoader parentLoader = loader.getParent();
        System.out.println(parentLoader);

    }

    /**
     * 双亲委派模型 - BootStrapClassLoader
     */
    @Test
    public void go2() {
        ClassLoader loader =
                Go.class.getClassLoader();
        ClassLoader parentLoader = loader.getParent();
        ClassLoader grandLoader = parentLoader.getParent();
        System.out.println(grandLoader);
    }


    /**
     * 使用ExtClassLoader
     */
    @Test
    public void go3() {
        ClassLoader loader =
                Go.class.getClassLoader();
        ClassLoader parentLoader = loader.getParent();

        try {
            parentLoader.loadClass("com.jarvis.Go2Test");
            System.err.println("ExtClassLoader load success");
        } catch (ClassNotFoundException e) {
            System.err.println("ExtClassLoader load failed");
        }


        try {
            loader.loadClass("com.jarvis.Go2Test");
            System.err.println("AppClassLoader load success");
        } catch (ClassNotFoundException e) {
            System.err.println("AppClassLoader load failed");
        }
    }


    private final String className = "com.jarvis.projectb.NotExist";

    /**
     * 用自定义的ClassLoader,加载不在class path 下的 jar
     */
    @Test
    public void go4() {
        try {
            NetworkClassLoader l= getClassLoader();
            Class clazz = l.loadClass(className);
            System.out.println(String.format("加载到类: %s " ,clazz.getName()));
            System.out.println(String.format("用的class loader 是: %s",clazz.getClassLoader()));

            // 指定了自定义的classloader
            System.out.println(String.format("再次加载class，class实例是否相等 : %s",clazz == Class.forName(className, true, l)));


            // 再new 出来一个classloader
            // 用这个示例，new出来的class ，是不同的
            NetworkClassLoader l0 = getClassLoader();
            System.out.println("又新建了一个class loader");
            Class clazz0 = l0.loadClass(className);
            System.out.println(String.format("用新建的loader加载的class 和上次是否相等 :%s",clazz == clazz0));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private NetworkClassLoader getClassLoader() {
        NetworkClassLoader loader = new NetworkClassLoader();
        loader.setBaseUrl("file:////Users/zjx/development/workspacenew/jvm-sample/");
        loader.addURL("projectb-1.0-SNAPSHOT.jar");
        return loader;
    }
}
