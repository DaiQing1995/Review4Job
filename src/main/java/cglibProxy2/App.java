package cglibProxy2;

public class App {
	
	public static void main(String[] args) {
		DaiQing dd = new DaiQing();
		DaiQing proxy = (DaiQing) new ProxyFactory(dd).getProxyInstance();
		proxy.playViolin();
	}
	
}
