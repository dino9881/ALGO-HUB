import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, M, K;
    static long arr[];

    public void update(int idx, long diff) {
        if (idx > N)
            return;
        arr[idx] += diff;
        int next = idx & -idx;
        update(idx + next, diff);
    }

    public long sum(int idx) {
        if (idx <= 0)
            return 0;
        int next = idx & -idx;
        return arr[idx] + sum(idx - next);
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            long n = Long.parseLong(br.readLine());
            update(i, n);
        }
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op == 1) { // update
                int idx = Integer.parseInt(st.nextToken());
                long k = Long.parseLong(st.nextToken());
                long diff = k - (sum(idx) - sum(idx - 1));
                update(idx, diff);
            } else {
                int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
                sb.append(sum(e) - sum(s - 1)).append("\n");
            }
        }
        System.out.print(sb);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}