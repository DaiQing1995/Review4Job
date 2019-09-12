package cglibProxy;

import java.util.LinkedList;

import org.junit.Test;

public class App {

    @Test
    public void test(){
        //目标对象1
        UserDao target = new UserDao();

        //代理对象
        UserDao proxy = (UserDao)new ProxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
    }
}