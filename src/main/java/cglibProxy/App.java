package cglibProxy;

import java.util.LinkedList;

import org.junit.Test;

public class App {

    @Test
    public void test(){
        //Ŀ�����1
        UserDao target = new UserDao();

        //�������
        UserDao proxy = (UserDao)new ProxyFactory(target).getProxyInstance();

        //ִ�д������ķ���
        proxy.save();
    }
}