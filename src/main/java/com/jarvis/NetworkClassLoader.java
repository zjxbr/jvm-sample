package com.jarvis;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.util.Enumeration;
import java.util.jar.Manifest;

/**
 * Created by zjx on 15/10/10.
 */


public class NetworkClassLoader extends URLClassLoader {

    String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public NetworkClassLoader() {
        this(new URL[]{});
    }

    /**
     * URL 以'/'结尾的为目录
     * 否则为jar包
     * 未指定其父类加载器为系统类加载器
     *
     * @param urls
     */
    public NetworkClassLoader(URL[] urls) {
        super(urls);
    }

    /**
     * 同上，指定classLoader
     *
     * @param urls
     * @param parent
     */
    public NetworkClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    /**
     * 同上,URL工厂处理器
     *
     * @param urls
     * @param parent
     * @param factory
     */
    public NetworkClassLoader(URL[] urls, ClassLoader parent,
                              URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    /**
     * [添加baseUrl]
     *
     * @param url
     */
    public void addURL(String url) {
        URL uurl = null;
        try {
            uurl = new URL(baseUrl + url);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        addURL(uurl);
    }

    /**
     * 添加url[添加baseUrl]
     */
    protected void addURL(URL url) {
        super.addURL(url);
    }

    /**
     * 返回urls
     */
    public URL[] getURLs() {
        return super.getURLs();
    }

    /**
     * 查找类对象
     * 从以上的URLS中查找加载当前类对象[会打开所有的jars去查找指定的类]
     * (可以通过调用findClass来得到以上URL加载包中的类)
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    /**
     * defineClass SecureClassLoader定义为最终方法，不允许更改.
     * 在使用这个类对象前，必须先resolved(解析)
     */


    /**
     * 查找资源[自定义相对URL查找路径]
     * 从以上的URLS中查找当前名称的资源
     * 这个必须重写，因为是public 哈哈
     */
    @Override
    public URL findResource(String name) {
        URL url = null;
        try {
            url = new URL(baseUrl + name);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 查找资源列表[URL查找路径]
     */
    public Enumeration<URL> findResources(String name) throws IOException {
        return super.findResources(name);
    }

    /**
     * 在当前的ClassLoader中，定义一个新的Package,Package的属性由Manifest指定.这个包的源文件
     */
    protected Package definePackage(String name, Manifest man, URL url)
            throws IllegalArgumentException {
        return super.definePackage(name, man, url);
    }

    /**
     * 加载路径权限
     */
    protected PermissionCollection getPermissions(CodeSource codesource) {
        return super.getPermissions(codesource);
    }

}
