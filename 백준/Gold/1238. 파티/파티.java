import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main
 */
public class Main {

    static int N, M, X, ans;
    static int dist[][];
    static PriorityQueue<Pair> pq;
    static List<List<Pair>> graph = new ArrayList<>();

    /**
     * Pair
     */
    public class Pair implements Comparable<Pair>{
        
        int node, weight;

        Pair(int node, int weight){
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Main.Pair o) {
            return Integer.compare(weight, o.weight);
        }
    }

    public void solve(int start){
        pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));
        while (!pq.isEmpty()){
            Pair p = pq.poll();
            int node = p.node;
            int w = p.weight;
            if (dist[start][node] > w){
                dist[start][node] = w;
                for (int i = 0; i < graph.get(node).size(); i++)
                    pq.add(new Pair(graph.get(node).get(i).node, w + graph.get(node).get(i).weight));
            }
        }
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        dist = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
            for (int j = 0; j <= N; j++){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s, e, w;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());   
            w = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Pair(e, w));
        }
        for (int i = 1; i <= N; i++){
            solve(i);
        }
        for (int i = 1; i <= N; i++){
            ans = Math.max(dist[X][i] + dist[i][X], ans);
        }
        System.out.println(ans);
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}