package com.cg;

public class TestDemo implements InterTestDemo{
    private String cg;
    private Apple apple;

    public String getCg() throws InterruptedException {
        System.out.println("开始计时：");
        Thread.sleep(1000);
        return cg;
    }

    public Apple getApple() {
        return apple;
    }
}
