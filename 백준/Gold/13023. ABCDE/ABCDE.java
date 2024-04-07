import java.io.*;
import java.util.*;

public class Main {
    static boolean end;
    static int N, M;
    static boolean[] visit;
    static ArrayList<Integer>[] graph;

    public void dfs(int node, int level) {
        if (end || level == 5) {
            end = true;
            return;
        }
        for (int i = 0; i < graph[node].size(); i++) {
            int next = graph[node].get(i);
            if (!visit[next]) {
                visit[next] = true;
                dfs(next, level + 1);
                visit[next] = false;
            }
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }

        for (int i = 0; i < N; i++) {
            visit = new boolean[N];
            visit[i] = true;
            dfs(i, 1);
            if (end) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}