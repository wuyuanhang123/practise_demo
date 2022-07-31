package com.example.startThread;

public class RunnableDemo implements Runnable {
    @Override
    public void run() {
        System.out.println("runnable线程启动");
    }

    public static void main(String[] args){
        Thread thread = new Thread(new RunnableDemo());
        thread.start();
    }
}
