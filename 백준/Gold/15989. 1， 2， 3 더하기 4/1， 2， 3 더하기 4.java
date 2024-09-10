import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    
    static int dp[][];

    



    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dp = new int[10000 + 1][3 + 1];
        
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 2;
        dp[3][3] = 1;
        for (int i = 4; i <= 10000; i++){
            dp[i][1] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3];
            dp[i][2] = dp[i - 2][2] + dp[i - 2][3];
            dp[i][3] = dp[i - 3][3];
            // 5 = 4 +  3 - 2 + 2 - 1
        }
        // for (int i = 1; i < 10; i++){
        //     System.out.println(dp[i][1] + " " + dp[i][2] + " " + dp[i][3]);
        // }
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0){
            int k = Integer.parseInt(br.readLine());
            sb.append(dp[k][1] + dp[k][2] + dp[k][3]).append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}