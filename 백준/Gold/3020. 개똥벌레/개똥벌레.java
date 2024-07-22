import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, H;
    static int[] memo, dp;

    public void solution()throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        memo = new int[H + 2];
        dp = new int[H + 2];
        for (int i = 0; i < N; i++){
            int k = Integer.parseInt(br.readLine());
            if (i % 2 == 0){
                // 석순
                memo[1]++;
                memo[k + 1]--;
            }          
            else{
                // 종유석
                memo[H - k + 1]++;
            }  
        }
        int m = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i <= H; i++){
            dp[i] = dp[i - 1] + memo[i];
            m = Math.min(dp[i], m);
        }
        for (int i = 1; i <= H; i++){
            if (dp[i] == m)
                cnt++;
        }
        System.out.println(m + " " + cnt);
    }

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}       