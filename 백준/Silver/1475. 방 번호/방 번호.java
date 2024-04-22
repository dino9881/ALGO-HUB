
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * Main
 */
public class Main {

    static int count;

    static int[] arr = new int[10];

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '9')
                c = '6';
            if (arr[c - '0'] == 0) {
                for (int j = 0; j < 10; j++) {
                    arr[j]++;
                }
                arr[6]++;
                count++;
            }
            arr[c - '0']--;
        }
        System.out.println(count);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}