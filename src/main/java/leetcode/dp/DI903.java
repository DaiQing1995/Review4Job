package leetcode.dp;

/**
903. Valid Permutations for DI Sequence

We are given S, a length n string of characters from the set {'D', 'I'}. (These letters stand for "decreasing" and "increasing".)

A valid permutation is a permutation P[0], P[1], ..., P[n] of integers {0, 1, ..., n}, such that for all i:

If S[i] == 'D', then P[i] > P[i+1], and;
If S[i] == 'I', then P[i] < P[i+1].
How many valid permutations are there?  Since the answer may be large, return your answer modulo 10^9 + 7.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-permutations-for-di-sequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author DaiQing
 */
public class DI903 {
    public int numPermsDISequence(String S) {
        int[][] dp = new int[S.length() + 1][S.length() + 1];
        char[] str = (" " + S).toCharArray();
        dp[0][0] = 1;
        for (int i = 1; i < str.length; ++ i){
            for (int j = 0; j <= i; ++ j){
                if (str[i] == 'D'){
                    for (int k = j; k < i; ++ k){
                        dp[i][j] += dp[i - 1][k];
                        dp[i][j] = dp[i][j] % 1000_000_007;
                    }
                }else{
                    for (int k = 0; k < j; ++ k){
                        dp[i][j] += dp[i - 1][k];
                        dp[i][j] = dp[i][j] % 1000_000_007;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0;i < str.length; ++ i){
            ans += dp[str.length - 1][i];
            ans %= 1000_000_007;
        }
        return ans;
    }
}
