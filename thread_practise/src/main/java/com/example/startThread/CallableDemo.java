package com.example.startThread;

import java.util.concurrent.*;

public class CallableDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 2;
    }

     public static void main(String[] args) throws ExecutionException, InterruptedException {
        //callable一般通过配合线程池来使用
         ExecutorService executorService = Executors.newCachedThreadPool();
         CallableDemo callableDemo = new CallableDemo();
         Future<Integer> result = executorService.submit(callableDemo);
         // 注意调用get方法会阻塞当前线程，直到得到结果。
         // 所以实际编码中建议使用可以设置超时时间的重载get方法。
         System.out.println(result.get());
         
         //FutureTask能够保证在高并发环境下任务只执行一次
         FutureTask<Integer> futureTask = new FutureTask<>(new CallableDemo());
         executorService.submit(futureTask);
         System.out.println(futureTask.get());
     }
}
