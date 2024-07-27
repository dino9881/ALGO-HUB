import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int n, a, b, ans;
    static long[] arr;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());

        arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int start = 0, end = 0;

        // 일단 할인으로 다 사기
        for (int i = 0; i < a; i++) {
            if (b >= (arr[i] / 2)) {
                end++;
                b -= arr[i] / 2;
            }
        }
        while (end != n) {
            // 예산 남으면 앞에 할인받은걸 뒤로 빼서 살수 있는지 본다.
            if (b >= ((arr[start] / 2) + (arr[end] / 2))) {
                b -= (arr[start] / 2) + (arr[end] / 2);
                end++;
                start++;
                continue;
            }
            break;

        }
        System.out.println(end);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}