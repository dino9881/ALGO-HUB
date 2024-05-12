import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N;
    static ArrayList<Integer>[] list;
    static int[] time;
    static int[] p, ans;
    static Queue<Integer> q;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        p = new int[N + 1];
        ans = new int[N + 1];
        time = new int[N + 1];
        q = new ArrayDeque<>();
        for (int i = 0; i<= N; i++){
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int next = Integer.parseInt(st.nextToken());
                if (next != -1){
                    list[next].add(i);
                    p[i]++;
                }
            }
            if (p[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int d = q.poll();
            ans[d] += time[d];
            for (int n : list[d]){
                p[n]--;
                ans[n] = Math.max(ans[n], ans[d]);
                if (p[n] == 0){
                    q.add(n);
                }
            }
        }
        for (int i = 1; i < ans.length; i++){
            System.out.println(ans[i]);
        }

    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}