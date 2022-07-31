package com.example.startThread;

public class ThreadDemo extends Thread{
    @Override
    public void run() {
        System.out.println("thread启动");
    }

    public static void main(String[] args){
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
    }
}
