import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int V, E;
    static int[][] graph;
    static int[][] dist;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new int[V][V];
        dist = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = 4000001;
            }
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s, e, d;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            graph[s - 1][e - 1] = d;
        }
        // System.out.println(Arrays.deepToString(graph));
        dist = graph;
        for (int m = 0; m < V; m++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    graph[i][j] = Math.min(graph[i][j], dist[i][m] + dist[m][j]);
                }
            }
            dist = graph;
        }
        int ans = 4000001;
        for (int i = 0; i < V; i++) {
            ans = Math.min(ans, graph[i][i]);
        }
        System.out.println(ans == 4000001 ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}