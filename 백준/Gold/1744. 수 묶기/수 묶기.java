import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, ans;
    static PriorityQueue<Integer> min, max;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        min = new PriorityQueue<>();
        max = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n <= 0)
                min.add(n);
            else
                max.add(n);
        }

        while (!min.isEmpty()) {
            int k = min.poll();
            if (!min.isEmpty()) {
                k *= min.poll();
            }
            ans += k;
        }

        while (!max.isEmpty()) {
            int k = max.poll();
            if (!max.isEmpty() && k * max.peek() > k + max.peek()) {
                k *= max.poll();
            }
            ans += k;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}