package com.example.myweb.utils;

public class ThreadLocalUtil {
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal<>();

    //取得ThreadLocal
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }

    //設定ThreadLocal
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }

    //移除ThreadLocal
    public static void remove(){
        THREAD_LOCAL.remove();;
    }
}
