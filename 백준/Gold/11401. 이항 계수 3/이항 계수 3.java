import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, R;
    static long[] fa;
    final static long MOD = 1000000007;

    public long pow(long n, long k) {
        if (n == 0)
            return 0;
        if (k == 0)
            return 1;
        if (k == 1)
            return n;
        if (k % 2 == 0) {
            long ret = pow(n, k / 2);
            return (ret * ret) % MOD;
        } else {
            long ret = pow(n, k / 2);
            return (((ret * ret) % MOD) * n) % MOD;
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        fa = new long[4000000 + 1];
        fa[0] = 1;
        fa[1] = 1;
        for (int i = 1; i <= 4000000; i++) {
            fa[i] = (fa[i - 1] * i) % MOD;
        }

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // System.out.println(fa[N]);
        // System.out.println(fa[N - R]);
        // System.out.println(fa[R]);

        System.out.println(fa[N] * (pow((fa[N - R] * fa[R]) % MOD, MOD - 2)) % MOD);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}