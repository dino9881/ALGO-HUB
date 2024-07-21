import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N;
    static int[] memo, dp;

    public void solution()throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        memo = new int[1000000 + 2];
        dp = new int[1000000 + 2];

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            memo[s]++;
            memo[e + 1]--;
        }

        for (int i = 1; i <= 1000000; i++){
            dp[i] = dp[i - 1] + memo[i];
        }
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            int q = Integer.parseInt(st.nextToken());
            sb.append(dp[q]).append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception{
        new Main().solution();
        
    }
}       