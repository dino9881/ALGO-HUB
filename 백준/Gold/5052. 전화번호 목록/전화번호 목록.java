import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static Set<String> set;
    static int T, N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            List<String> list = new ArrayList<>();
            set = new HashSet<>();
            boolean flag = false;
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                String tok = br.readLine();
                list.add(tok);
                for (int j = 0; j < tok.length(); j++) {
                    set.add(tok.substring(0, j));
                }
            }
            for (int i = 0; i < N; i++) {
                if (set.contains(list.get(i))) {
                    sb.append("NO\n");
                    flag = true;
                    break;
                }
            }
            if (!flag)
                sb.append("YES\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}