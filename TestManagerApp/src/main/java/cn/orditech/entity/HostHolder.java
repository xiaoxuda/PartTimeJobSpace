package cn.orditech.entity;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/3/26.
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();
    public User getUser(){
        return users.get();
    }
    public void setUser(User user){
        users.set(user);
    }

    /**
     * 清楚当前线程对应的user
     */
    public void clear(){
        users.remove();
    }
}
