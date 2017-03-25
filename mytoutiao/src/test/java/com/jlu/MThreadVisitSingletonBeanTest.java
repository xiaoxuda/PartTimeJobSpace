package com.jlu;

import com.jlu.service.MThreadTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/7/18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MytoutiaoApplication.class)
public class MThreadVisitSingletonBeanTest {
    @Autowired
    private MThreadTestService mThreadTestService;

    @Test
    public void test(){
        for(int i=0;i < 3;i++){
            final int k=i;
            Runnable task=new Runnable() {
                @Override
                public void run() {
                    mThreadTestService.print();
                    try{
                    //Thread.sleep(1000);
                    mThreadTestService.visit();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            };
            new Thread(task).start();
        }
    }
}
