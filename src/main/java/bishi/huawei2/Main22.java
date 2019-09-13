package bishi.huawei2;
import java.util.*;
public class Main22{
    
    public static int getCommonSeq(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m][n];
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
        if (s1.length() == 1 && s2.length() == 1)
        	return dp[0][0];
        for (int i = 1;i < m; ++ i){
            if (s1.charAt(i) == s2.charAt(0)){
                dp[i][0] = 1;
            }else{
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1;i < n; ++ i){
            if (s2.charAt(i) == s1.charAt(0)){
                dp[0][i] = 1;
            }else{
                dp[0][i] = dp[0][i - 1];
            }
        }
        
        for (int i = 1;i < m; ++ i){
            for (int j = 1; j < n; ++ j){
                int tmp = dp[i - 1][j - 1];
                if (s1.charAt(i) == s2.charAt(j)){
                    tmp ++;
                }
                dp[i][j] = tmp > dp[i][j - 1] ? tmp : dp[i][j - 1];
                dp[i][j] = dp[i][j] > dp[i - 1][j] ? dp[i][j] : dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }
    
    public static void main(String[] args){
        String s1,s2;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] tmp = line.split(" ");
            if (tmp.length < 2) {
            	continue;
            }
            s1 = tmp[0];
            s2 = tmp[1];
            System.out.println(getCommonSeq(s1, s2));
        }
        sc.close();
    }
}
