
import java.io.*;
import java.util.*;


public class Main {

    static String s;
    static long dp[];
    static int num[];
  
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        num = new int[s.length() + 1];
        dp = new long[s.length() + 1];
        for (int i = 0; i < s.length(); i++){
            int k = Integer.valueOf(s.charAt(i)) - '0';
            num[i + 1] = k;
        }
        // System.out.println(Arrays.toString(num));
        if (num[1] == 0){
            System.out.println(0);
            return;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < num.length; i++){

            if (num[i] == 0){
                if (num[i - 1] == 0 || num[i - 1] > 2)
                {
                    System.out.println(0);
                    return;
                }
                else{
                    dp[i] = dp[i - 2];
                    dp[i - 1] = dp[i];
                    continue;
                }
            }
            if (num[i - 1] > 2){
                dp[i] = dp[i - 1];
                continue;
            }
            if (num[i - 1] == 2){
                if (num[i] > 6){
                    dp[i] = dp[i - 1];
                continue;
                }
            }
            if (num[i - 1] == 0){
                dp[i] = dp[i -  1];
                continue;
            }
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
        }
        // System.out.println(Arrays.toString(dp));
        System.out.println(dp[s.length()]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}