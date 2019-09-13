package bishi.vivo;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Welcome to vivo !
 */

/**
 * 消消乐获得最大得分
 * 输入数字表示颜色，考虑先消除哪一个，最后获得最大分。
 * 
 * TODO：还未写完该题，计划dfs写法。
 * @author DaiQing
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int input[] = parseInts(inputStr.split(" "));
        int output = solution(input);
        System.out.println(output);
    }

    private static int[] parseInts(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new int[0];
        }
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    private static int solution(int[] input) {
    	if (input == null || input.length == 0 || input.length == 1)
    		return 0; 
    	int tmpContinue = 1;
    	int tmpIndex = 0;
    	int maxScore = 0;
    	for (int j = 1; j < input.length; ++ j) {
    		if (input[j] == input[tmpIndex]) {
    			tmpContinue ++;
    		}else {
    			if (tmpContinue >= 2)
    				maxScore = Math.max(maxScore, getScore(input, tmpIndex));
    			tmpContinue = 1;
    			tmpIndex = j;
    		}
    	}
    	return getScore(input, tmpIndex);
    }

	private static int getScore(int[] input, int tmpIndex) {
		int i = 0;
		int ret = 0;
		for (i = tmpIndex; i >= 0 && input[tmpIndex] == input[i]; -- i);
		if (tmpIndex - i == input.length)
			return (tmpIndex - i) * (tmpIndex - i);
		int[] newInput = new int[input.length - (tmpIndex - i)];
		int count = 0;
		for (int j = 0; j < input.length; ++ j) {
			if (!(j > i && j < tmpIndex)) {
				newInput[count ++] = input[j];
			}
		}
		int tmpContinue = 1;
    	tmpIndex = 0;
    	int maxScore = 0;
    	for (int j = 1; j < input.length; ++ j) {
    		if (input[j] == input[tmpIndex]) {
    			tmpContinue ++;
    		}else {
    			if (tmpContinue >= 2)
    				maxScore = Math.max(maxScore, getScore(input, tmpIndex));
    			tmpContinue = 1;
    			tmpIndex = j;
    		}
    	}
		return ret;
	}
}