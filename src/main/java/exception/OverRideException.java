package exception;

import java.io.IOException;

public class OverRideException {
	
	private static class Father{
		public void throwIOException() throws Exception {
			throw new IOException();
		}
	}
	
	private static class Child extends Father{
		/**
		 * 覆盖写并不需要抛异常
		 */
		@Override
		public void throwIOException(){
			System.out.println("dsadsa");
		}
	}
	
	public static void main(String[] args) throws Exception {
		Father test = new Child();
		test.throwIOException();
	}
}
