package designpattern.state;

public class App {
	public static void main(String[] args) {
		DaiQing[] dqs = new DaiQing[] {new DaiQing(new HappyMood()), new DaiQing(new SadMood())};
		for (int i =  0;i < dqs.length; ++ i) {
			System.out.println("It's " + (1 + i) + "th DaiQing");
			dqs[i].talkWithDaiQing();
		}
	}
}
