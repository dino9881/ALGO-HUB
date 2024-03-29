import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int N, M, ans;
    static int[] arr;
    static ArrayList<Integer> start, hegiht;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        start = new ArrayList<>();
        hegiht = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (M < arr[i]) {
                start.add(i);
                hegiht.add(arr[i]);
            } else if (M > arr[i]) {
                Queue<Integer> queue = new ArrayDeque<>();
                for (int idx = hegiht.size() - 1; idx >= 0; idx--){
                    if (hegiht.get(idx) > arr[i]){
                        // System.out.println("!" + idx);
                        queue.add(idx);
                    }
                }

                while (queue.size() > 1){
                    int idx = queue.poll();
                    // System.out.println("idx = " + idx + " num = " + arr[i]);
                    ans = Math.max(ans, (i - start.get(idx)) * hegiht.get(idx));
                    start.remove(idx);
                    hegiht.remove(idx);
                }
                if (!queue.isEmpty()){
                    int idx = queue.poll();
                    ans = Math.max(ans, (i - start.get(idx)) * hegiht.get(idx));
                    hegiht.set(idx, arr[i]);
                }
            }
            M = arr[i];
        }
        for (int i = 0; i < start.size(); i++) {
            // System.out.println("------------------");
            // System.out.println(start.get(i));
            // System.out.println(hegiht.get(i));
            // System.out.println((N - start.get(i)) * hegiht.get(i));
            // System.out.println("------------------");
            ans = Math.max(ans, (N - start.get(i)) * hegiht.get(i));
        }
        System.out.println(ans);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}