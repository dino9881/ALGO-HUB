import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, M, ans;
    static int p[];
    static List<Integer>[] pointed, pointing;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pointed = new List[N + 1];
        pointing = new List[N + 1];

        for (int i = 0; i <= N; i++) {
            pointed[i] = new ArrayList<>();
            pointing[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
            pointing[s].add(e);
            pointed[e].add(s);
        }

        for (int i = 1; i <= N; i++) {
            boolean[] visit = new boolean[N + 1];
            int p = 0;
            int n = 0;
            Queue<Integer> q = new ArrayDeque<>();
            for (int node : pointed[i]) {
                q.add(node);
                visit[node] = true;
            }
            while (!q.isEmpty()) {
                p++;
                int cur = q.poll();
                for (int next : pointed[cur]) {
                    if (!visit[next]) {
                        visit[next] = true;
                        q.add(next);
                    }
                }
            }

            q = new ArrayDeque<>();
            for (int node : pointing[i]) {
                q.add(node);
                visit[node] = true;
            }
            while (!q.isEmpty()) {
                n++;
                int cur = q.poll();
                for (int next : pointing[cur]) {
                    if (!visit[next]) {
                        visit[next] = true;
                        q.add(next);
                    }
                }
            }
            // System.out.println(p + ", " + n);
            if (p + n == N - 1) {
                ans++;
            }
        }
        System.out.println(ans);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}