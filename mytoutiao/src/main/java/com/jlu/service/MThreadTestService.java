package com.jlu.service;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/7/18.
 */
@Service
public class MThreadTestService {
    public void print(){
        System.out.println("当前线程in："+Thread.currentThread().getName());
        System.out.println("线程out："+Thread.currentThread().getName());
//        System.out.println("线程out："+Thread.currentThread().getName());
    }

    public void visit(){
        System.out.println("visit："+Thread.currentThread().getName());
        try{
            System.out.println("visit over："+Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("异常"+e.getMessage());
        }
//        System.out.println("线程out："+Thread.currentThread().getName());
    }
}
