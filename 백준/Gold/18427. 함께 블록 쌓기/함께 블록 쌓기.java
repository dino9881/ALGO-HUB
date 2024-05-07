
import java.io.*;
import java.util.*;


public class Main {

    static int N, M, H;
    static int[][] dp;
    static final int SIZE = 1000;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        dp = new int[2][SIZE + 1];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int tmp[] = new int[10];
            int j = 0;
            while (st.hasMoreTokens()){
                tmp[j] = Integer.parseInt(st.nextToken());
                for (int k = 0; k <= SIZE; k++){
                    int num = k + tmp[j];
                    // System.out.println(num + "!!");
                    if (num > SIZE)
                    break;
                    if (k == 0){
                        dp[1][num] += 1;
                        dp[1][num] %= 10007;
                    }
                    else if (dp[0][k] != 0){
                        dp[1][num] += dp[0][k];
                        dp[1][num] %= 10007;
                    }
                }
                // System.out.println(dp[1][1000] + "!@#");
            }
            for (j = 0; j <= SIZE; j++){
                dp[1][j] += dp[0][j];
                dp[1][j] %= 10007;
            }
            dp[0] = dp[1];
            dp[1] = new int[SIZE + 1];
            // System.out.println(Arrays.toString(dp[0]));
            // System.out.println(Arrays.toString(dp[1]));
            j++;
        }
        // System.out.println(Arrays.toString(dp[0]));
        System.out.println(dp[0][H]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}