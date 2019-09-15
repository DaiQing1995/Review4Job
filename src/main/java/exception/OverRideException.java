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
		 * ����д������Ҫ���쳣
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
