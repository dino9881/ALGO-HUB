import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int N;
    static int[] pi;
    static String str, pat;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        str = br.readLine();
        pat = br.readLine();
        pi = new int[pat.length()];
        int j = 0;
        for (int i = 1; i < pat.length(); i++) {
            while (j > 0 && pat.charAt(i) != pat.charAt(j)) {
                j = pi[j - 1];
            }
            if (pat.charAt(i) == pat.charAt(j)) {
                pi[i] = ++j;
            }
        }
        j = 0;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pat.charAt(j)) {
                j = pi[j - 1];
            }
            if (str.charAt(i) == pat.charAt(j)) {
                if (j != pat.length() - 1) {
                    j++;
                } else {
                    sb.append(i + 1 - pat.length() + 1).append("\n");
                    count++;
                    j = pi[j];
                }
            }
        }
        System.out.println(count);
        if (count != 0)
            System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}