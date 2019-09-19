package jdk8;

/**
 * Lamda expression:
 * (parameters) -> (do something with parameters)
 * 
 * @author DaiQing
 *
 */
public class LamdaExpression {

	private interface MathOperation{
		int operation(int a, int b);
	}
	
	private interface GreetingService{
		void sayMessage(String msg);
	}
	
	private static int operate(int a, int b, MathOperation operation) {
		return operation.operation(a, b);
	}
	
	public static void main(String[] args) {
		MathOperation addition = (int x, int y) -> x + y;
		MathOperation substraction = (x, y) -> x - y; //emit type declear
		MathOperation multiplication = (int a, int b) -> {return a * b;};
		MathOperation division = (int a, int b) -> a / b;

		System.out.println("10 + 5 = " + operate(10, 5, addition));
		System.out.println("10 - 5 = " + operate(10, 5, substraction));
		System.out.println("10 * 5 = " + operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + operate(10, 5, division));
		
		GreetingService greetingService = message -> System.out.println("Hello " + message);
		GreetingService greetingService1 = (message) -> System.out.println("Hey " + message);

		greetingService.sayMessage("dq");	
		greetingService1.sayMessage("mm");		
	}
}
