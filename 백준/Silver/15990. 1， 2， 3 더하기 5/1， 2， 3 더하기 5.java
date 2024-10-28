import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int N;
    static long[][] dp;
    static long[]ans;

    

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        dp = new long[4][100_000 + 1];
        ans = new long[100_000 + 1];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][3] = 1;
        dp[1][3] = 1;
        dp[2][3] = 1;
        ans[1] = 1;
        ans[2] = 1;
        ans[3] = 3;
         for (int i = 4; i <= 100_000; i++){
            dp[1][i] = (dp[2][i - 1] + dp[3][i - 1]) % 1000000009;
            dp[2][i] = (dp[1][i - 2] + dp[3][i - 2]) % 1000000009;
            dp[3][i] = (dp[1][i - 3] + dp[2][i - 3]) % 1000000009;
            ans[i] = (dp[1][i] + dp[2][i] + dp[3][i]) % 1000000009; 
         }
        for (int i = 0; i < N; i++){
            int k = Integer.parseInt(br.readLine());
            sb.append(ans[k]).append("\n");
        }
        System.out.print(sb);
    
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}