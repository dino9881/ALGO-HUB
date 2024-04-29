
import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    int graph[][];
    int n, m, ans;

    /**
     * Data
     */
    public class Data implements Comparable<Data>{
        int node, dist;

        Data(int node, int dist){
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Main.Data o) {
            return Integer.compare(dist, o.dist);
        }
    }

    public int dijk(int start, int end){
        int dist[] = new int[n + 1];
        for (int i = 0; i <= n; i++){
            dist[i] = Integer.MAX_VALUE - 1000000;
        }
        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.add(new Data(start, 0));
        while (!pq.isEmpty()) {
            Data d = pq.poll();
            int val = d.dist;
            int node = d.node;
            if (dist[node] < val)
                continue;
            dist[node] = val;
            for (int next = 0; next <= n; next++) {
                int nextD = val + graph[node][next];
                if (nextD < dist[next]){
                    dist[next] = nextD;
                    pq.add(new Data(next, nextD));
                } 
            }
        }
        // System.out.println(Arrays.toString(dist));
        return dist[end];
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i != j)
                    graph[i][j] = 10000000;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()),
                    d = Integer.parseInt(st.nextToken());
            if (graph[s][e] > d) {
                graph[s][e] = d;
                graph[e][s] = d;
            }
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // System.out.println(Arrays.deepToString(graph));
        // System.out.println(dijk(1, v1));
        // System.out.println(dijk(1, n));
        ans = Integer.MAX_VALUE;
        ans = Math.min(ans, dijk(1, v1) + dijk(v1, v2) + dijk(v2, n));
        ans = Math.min(ans, dijk(1, v2) + dijk(v2, v1) + dijk(v1, n));
        System.out.println(ans >= 10000000  ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}