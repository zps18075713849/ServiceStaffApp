package com.haitian.servicestaffapp;

import java.net.Socket;

/**
 * Created by Android Studio.
 * User: user
 * Date: 2021/3/18
 * Time: 15:16
 */
public class test {
    public static void main(String[] args) {

        Runnable runnable1 = new Runner1();
        Runnable runnable2 = new Runner2();
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();

//        thread1.run();
//        thread2.run();    18+6   24
    }

   static class Runner1 implements Runnable{
       @Override
       public void run() {
           for (int i=0;i<100;i++){
               System.out.println(Thread.currentThread().getName()+"进入Runner1运行"+i);
           }
       }
   }
   static class Runner2 implements Runnable{
       @Override
       public void run() {
           for (int i=0;i<100;i++){
               System.out.println(Thread.currentThread().getName()+"进入Runner2运行"+i);
           }
       }
   }
}
