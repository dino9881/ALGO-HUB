import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, M;
    static int ans = Integer.MAX_VALUE;
    static boolean visit[];
    static int dp[][];
    static int data[][] = {{1, 10000}, {3, 25000}, {5, 37000}};
    static int coupon[] = {0, 1, 2};



    public void solution()throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[N + 2];
        dp = new int[N + 2][220 + 1];
        for (int i = 1; i <= N; i++){
            visit[i] = true;
        }
        if (M != 0){
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++){
                int d = Integer.parseInt(st.nextToken());
                visit[d] = false;
            }
        }
        for (int i = 0; i <= N; i++){
            for (int j = 0; j < 220; j++){
                dp[i][j] = Integer.MAX_VALUE - 10000000;
            }
        }
        
        dp[1][0] = 0;
        for (int i = 1; i <= N; i++){
            for (int j = 0; j <= 210; j++){
                if (dp[i][j] == Integer.MAX_VALUE - 10000000)
                    continue;
                // System.out.println(i + " " + j + " = " + dp[i][j]);
                if (!visit[i]){
                    if (i + 1 > N)
                        ans = Math.min(dp[i][j], ans);
                    dp[i + 1][j] = dp[i][j];
                    continue;
                }
                int curCost = dp[i][j]; // 현재 날짜, 쿠폰상황까지 오기까지의 최소 돈값
                int nextday;
                int nextCost;
                // 쿠폰 사용 가능할때
                if (j >= 3){
                    if (i + 1 > N){
                        ans = Math.min(curCost, ans);
                    }
                    else{
                        dp[i + 1][j - 3] = Math.min(dp[i + 1][j -3], curCost);
                    }
                }
                for (int k = 0; k < 3; k++){
                    nextday = i + data[k][0];
                    nextCost = curCost + data[k][1];
                    if (nextday > N){
                        ans = Math.min(nextCost, ans);
                        continue;
                    }
                    dp[nextday][j + coupon[k]] = Math.min(dp[nextday][j + coupon[k]], nextCost);
                }
            }
        }
        
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}       