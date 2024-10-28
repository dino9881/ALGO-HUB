import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int N;
    static int[] dp;
    static Queue<Data> q[];

    /**
     * Data
     */
    public class Data {
        int T, P;
        Data(int t, int p){
            T = t;
            P = p;
        }
        @Override
        public String toString() {
            
            return "(" + T + ", " + P + ")";
        }
    }

    

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        q = new Queue[N + 2];
        dp = new int[N + 2];
        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int d = i + t;
            if (d > N + 1)
                continue;
            if (q[d] == null){
                q[d] = new ArrayDeque<>();
            }
            q[d].add(new Data(t, p));
        }
        // System.out.println(Arrays.toString(q));

        for (int i = 1; i <= N + 1; i++){
            dp[i] = dp[i - 1];
            if (q[i] == null)
                continue;
            while (!q[i].isEmpty()){
                Data d = q[i].poll();
                dp[i] = Math.max(dp[i], dp[i - d.T] + d.P);
            }
        }
        System.out.println(dp[N + 1]);

    
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}