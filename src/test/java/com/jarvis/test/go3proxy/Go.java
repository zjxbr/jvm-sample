package com.jarvis.test.go3proxy;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zjx on 15/10/11.
 */
public class Go {

    interface JarvisJob {
        void doJob();
    }

    static class JarvisJobA implements JarvisJob {
        public void doJob() {
            System.out.println("Do jarvis job");
        }
    }

    static class JarvisProxy implements InvocationHandler {

//        a
        Object oriObj;

        Object beProxy(Object oriObj) {
            this.oriObj = oriObj;

            return Proxy.newProxyInstance(oriObj.getClass().getClassLoader(), oriObj.getClass().getInterfaces(), this);
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("我是代理");
            return method.invoke(oriObj, args);
        }
    }

    /**
     * Proxy 和InvocationHandler
     */
    @Test
    public void go() {
        JarvisJob jobA = new JarvisJobA();
        JarvisJob jarvisJob = (JarvisJob) (new JarvisProxy().beProxy(jobA));

        jarvisJob.doJob();

        System.out.println(String.format("我是啥类? %s", jarvisJob.getClass()));
        System.out.println(String.format("我和jobA的Class相等吗? %s", jarvisJob.getClass() == jobA.getClass()));
    }

    String test1ClassName = "com.jarvis.Test1";

    /**
     * javaassist 测试
     */
    @Test
    public void go1() throws Exception {

        loadClassByJavaA(test1ClassName);

        // Class.forName加载一下，出来的结果会是什么 ???
//            loadClass(test1ClassName);

    }

    /**
     * 用 javaassist加载
     * @param cName
     * @throws Exception
     */
    private void loadClassByJavaA(String cName) throws Exception {

        // 获得jboss 的class 池
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = null;


        // 获取类Test1的字节码
        cc = pool.get(cName);
        // 找到toString method
        CtMethod m = cc.getDeclaredMethod("toString");
        // 在执行之前 insert 一个 打印语句
        m.insertBefore("{ System.out.println(\"javaassist生效\"); }");

        // 生成一个新类
        Class clazz = cc.toClass();
        Object test1 = clazz.newInstance();
        System.out.println(test1.toString());
        System.out.println(test1.getClass().getClassLoader());

    }

    /**
     * 用 class forname 加载
     * @param cName
     */
    private void loadClass(String cName) {
        try {
            Class c = Class.forName(cName);
            System.out.println("用class forname 加载:" + c.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 先用系统加载类，再用javaassist加载
     *
     * @throws Exception
     */
    @Test
    public void go3() throws Exception {

        // 先用forName加载
        loadClass(test1ClassName);

        // 在用javaassit加载
//        loadClassByJavaA(test1ClassName);

    }

}
