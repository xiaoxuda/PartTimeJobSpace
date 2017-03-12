package com.jlu;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MyTest {
    public static void main(String [] args){
        SingletonBeanTest test=new SingletonBeanTest();
        for(int i = 0;i < 3;i++){
            final int k=0;
            new Thread(new Task(test)).start();
        }
    }
}

class Task implements Runnable{
    private SingletonBeanTest singletonBeanTest;
    public Task(SingletonBeanTest test){
        this.singletonBeanTest=test;
    }
    @Override
    public void run(){
        singletonBeanTest.print();
        try{
            //Thread.sleep(100);
            singletonBeanTest.visit();
        }catch (Exception e){
            e.printStackTrace();

        }

    }
}

class SingletonBeanTest{
    public void print(){
        System.out.println("当前线程in："+Thread.currentThread().getName());
        try{
            Thread.sleep(100);
            System.out.println("线程out："+Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("异常"+e.getMessage());
        }

//        System.out.println("线程out："+Thread.currentThread().getName());
    }

    public void visit(){
        System.out.println("visit："+Thread.currentThread().getName());
        try{
            Thread.sleep(100);
            System.out.println("visit over："+Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("异常"+e.getMessage());
        }
//        System.out.println("线程out："+Thread.currentThread().getName());
    }
}