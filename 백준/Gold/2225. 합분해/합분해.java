import java.io.*;
import java.util.*;


/**
 * Main
 */
public class Main {

    static int N, K;
    static long dp[][];

    public void solution()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[K + 1][N + 1];
        for (int i = 0; i <= N; i++){ // 1개의 수로 합을 만드는 방법
            dp[1][i] = 1;
        }

        for (int k = 2; k <= K; k++){
            for (int i = 0; i <= N; i++){
                for (int j = i; j >= 0; j--){
                    dp[k][i] += dp[k - 1][i - j];
                    dp[k][i] %= 1000000000;
                }
            }
        }
        
        System.out.println(dp[K][N]);
 
        
        
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}