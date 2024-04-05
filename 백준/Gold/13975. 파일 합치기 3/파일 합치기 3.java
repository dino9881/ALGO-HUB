import java.io.*;
import java.util.*;

public class Main {

    public int T, K;
    public PriorityQueue<Long> pq;
    public long ans;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            pq = new PriorityQueue<>();
            for (int i = 0; i < K; i++) {
                pq.add(Long.parseLong(st.nextToken()));
            }
            ans = 0;
            if (K == 1) {
                System.out.println(pq.peek());
                continue;
            }
            while (pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();
                ans += a + b;
                // System.out.println(a + " " + b + "---" + ans);
                pq.add(a + b);
            }
            System.out.println(ans);
            // System.out.println(pq.peek());

        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}