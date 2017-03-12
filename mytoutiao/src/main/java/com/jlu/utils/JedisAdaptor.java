package com.jlu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2016/7/20.
 */
@Component
public class JedisAdaptor implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);
    private JedisPool pool = null;

    public static void print(int index, Object obj) {
        System.out.println(String.format("%d,%s", index, obj.toString()));
    }

    public static void main(String[] args){
        Jedis jedis = new Jedis();

        jedis.setex("name",5,"100");
        String listName = "lista";
        for(int i= 0;i < 10 ;i++){
            jedis.lpush(listName,"a"+String.valueOf(i));
        }
        print(3,jedis.lrange(listName,0,12));
        print(4,jedis.llen(listName));

        //redis的 类似hashmap集合
        String userKey = "user12";
        jedis.hset(userKey,"name","jim");
        jedis.hset(userKey,"age","20");

        print(5,jedis.hget(userKey,"name"));


    }

    //这里其实就涉及到了bean的生命周期的概念，在Spring容器加载bean并实例化后，完成一些动作
    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("localhost",6379);
    }

    private Jedis getJedis(){
        return pool.getResource();
    }

    public long sadd(String key, String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.sadd(key, value);
        }catch (Exception e){
            logger.error("异常"+e.getMessage());
            return 0;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public long srem(String key, String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.srem(key, value);
        }catch (Exception e){
            logger.error("异常"+e.getMessage());
            return 0;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
