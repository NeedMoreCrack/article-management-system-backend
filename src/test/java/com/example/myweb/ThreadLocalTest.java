package com.example.myweb;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet(){
        ThreadLocal tl = new ThreadLocal();
        new Thread(() -> {
            tl.set("A");
            System.out.println(Thread.currentThread()+" : "+tl.get());
        },"Blue").start();
        new Thread(() -> {
            tl.set("B");
            System.out.println(Thread.currentThread()+" : "+tl.get());
        },"Green").start();
    }
}
