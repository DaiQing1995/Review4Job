package bishi.vivo;

import java.io.*;
import java.util.Stack;

/**
 * Welcome to vivo !
 */

public class TiMu1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int output = solution(inputStr );
        System.out.println(output);
    }

    private static int solution(String str) {
    	Stack<Character> stack = new Stack<>();
    	for (int i = 0; i < str.length(); ++ i) {
    		char ch = str.charAt(i);
    		if (ch == '(')
    			stack.push(ch);
    		else if (ch == ')') {
    			stack.pop();
    		}else {
    			return stack.size();
    		}
    	}
		return 0;
    }
}