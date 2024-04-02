import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, M;
    static ArrayList<Data>[] graph;
    static Map<Integer, Integer>[] dp;
    static int[] p;
    static List<Integer> ansList = new ArrayList<>();

    /**
     * Data
     */
    public class Data {
        int idx, count;

        Data(int i, int c) {
            idx = i;
            count = c;
        }

    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        dp = new HashMap[N + 1];
        p = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i <= N; i++) {
            dp[i] = new HashMap<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            graph[y].add(new Data(x, k));
            p[x]++;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < N; i++) {
            if (p[i] == 0) {
                // System.out.println(i);
                q.add(i);
                dp[i].put(i, 1);
                ansList.add(i);
            }
        }

        while (!q.isEmpty()) {
            int idx = q.poll();
            for (Data data : graph[idx]) {
                int next = data.idx;
                int count = data.count;
                p[next]--;
                if (p[next] == 0) {
                    q.add(next);
                }
                for (Map.Entry<Integer, Integer> entry : dp[idx].entrySet()) {
                    int key = entry.getKey();
                    int val = entry.getValue() * count;
                    dp[next].putIfAbsent(key, 0);
                    dp[next].put(key, dp[next].get(key) + val);
                }
            }
        }

        for (int idx : ansList) {
            if (dp[N].containsKey(idx) && dp[N].get(idx) != 0) {
                System.out.println(idx + " " + dp[N].get(idx));
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}