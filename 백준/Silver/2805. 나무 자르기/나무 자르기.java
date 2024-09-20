import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main
 */
public class Main {

    static int N, M;
    static int trees[];

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        trees = new int[N];
        for (int i = 0; i < N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees);
        int h = trees[N - 1];
        int l = 0;
        while (l <= h){
            int m = (l + h) / 2;
            long sum = 0;
            for (int i = N - 1; i >= 0; i--){
                if (trees[i] <= m )
                    break;
                sum += trees[i] - m;
            }
            if (sum >= M){
                l = m + 1;
            }
            else
                h = m - 1;
        }
        System.out.println(h);
    }
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}