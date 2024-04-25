
import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    int graph[][];
    int val[];
    int n, m, r, ans;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][n + 1];
        val = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            val[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i != j)
                    graph[i][j] = 1000000;
            }
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()),
                    d = Integer.parseInt(st.nextToken());
            if (graph[s][e] > d) {
                graph[s][e] = d;
                graph[e][s] = d;
            }
        }

        for (int mid = 1; mid <= n; mid++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][j] > graph[i][mid] + graph[mid][j]) {
                        graph[i][j] = graph[i][mid] + graph[mid][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] <= m)
                    count += val[j];
            }
            ans = Math.max(count, ans);
        }
        // System.out.println(Arrays.deepToString(graph));
        System.out.println(ans);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}