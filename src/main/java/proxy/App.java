package proxy;

public class App {
    public static void main(String[] args) {
        // Ŀ�����
        IUserDao target = new UserDao();
        // ��ԭʼ������ class cn.itcast.b_dynamic.UserDao��
        System.out.println(target.getClass());

        // ��Ŀ����󣬴����������
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   �ڴ��ж�̬���ɵĴ������
        System.out.println(proxy.getClass());
        if (proxy instanceof IUserDao)
        	System.out.println("asd");
        // ִ�з���   ���������
        proxy.save();
    }
}