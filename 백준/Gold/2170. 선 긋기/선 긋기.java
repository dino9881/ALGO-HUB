import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long ans;
    static ArrayList<Pair> list;

    /**
     * Pair
     */
    public class Pair {

        long start, end;

        Pair(long s, long e) {
            this.start = s;
            this.end = e;
        }

    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long s = Long.parseLong(st.nextToken()), e = Long.parseLong(st.nextToken());
            long m = 0, M = 0;
            int idx = 0;
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                Pair p = list.get(j);
                long start = p.start;
                long end = p.end;
                if (start > e || end < s) // 안겹쳐
                    continue;
                if (start >= s && end <= e) {
                    list.remove(j);
                    j--;
                    continue;
                }
                m = Math.min(start, s);
                M = Math.max(end, e);
                idx = j;
                flag = true;
            }
            if (!flag)
                list.add(new Pair(s, e));
            else {
                list.remove(idx);
                list.add(new Pair(m, M));
            }
            // System.out.println("+==============");
            // for (int key : map.keySet()) {
            // System.out.println(key + " " + map.get(key));
            // }
            // System.out.println("+==============");
        }

        for (Pair p : list) {
            ans += p.end - p.start;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}