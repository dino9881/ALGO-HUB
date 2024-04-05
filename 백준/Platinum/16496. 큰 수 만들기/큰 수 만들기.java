import java.io.*;
import java.util.*;

public class Main {

    public int N;
    public int[] arr;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {
            String a = o1 + o2;
            String b = o2 + o1;
            // int idx = 0;
            // while (idx < o1.length() && idx < o2.length() && o1.charAt(idx) ==
            // o2.charAt(idx)) {
            // idx++;
            // }
            // if (idx < o1.length() && idx < o2.length() && o1.charAt(idx) !=
            // o2.charAt(idx)) {
            // return Character.compare(o2.charAt(idx), o1.charAt(idx));
            // }
            // if (o1.length() < o2.length() && o1.charAt(o1.length() - 1) >=
            // o2.charAt(idx)) {
            // return -1;
            // } else
            // return 1;
            return -a.compareTo(b);
        });
        for (int i = 0; i < N; i++) {
            pq.add(st.nextToken());
        }
        if (pq.peek().charAt(0) == '0') {
            System.out.println(0);
            return;
        }
        while (!pq.isEmpty()) {
            System.out.print(pq.poll());
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}