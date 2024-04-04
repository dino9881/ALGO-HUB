import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static long ans;
    static ArrayList<String> list;

    public String trim(String s) {

        int idx = 0;
        if (s.length() == 1) {
            return s;
        }
        if (s.charAt(0) == '-')
            idx++;
        while (idx < s.length() && s.charAt(idx) == '0') {
            // System.out.println("!");
            idx++;
        }
        if (idx == s.length())
            return "0";
        if (s.charAt(0) == '-') {
            return "-" + s.substring(idx, s.length());
        }
        return s.substring(idx, s.length());

    }

    public long cal(long n1, long n2, String op) {
        switch (op.charAt(0)) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '/':
                return n1 / n2;
            case '*':
                return n1 * n2;
            default:
                return 0;
        }
    }

    public void solve() {
        String[] strings = list.toArray(new String[list.size()]);
        // System.out.println(Arrays.toString(strings));
        int s = 0, e = strings.length - 1;
        while (s < e) {
            String n1, op1, n2, n3, op2, n4;
            n1 = strings[s];
            op1 = strings[s + 1];
            n2 = strings[s + 2];
            n4 = strings[e];
            op2 = strings[e - 1];
            n3 = strings[e - 2];
            if (p(op1) == p(op2)) {
                long ret1 = cal(Long.parseLong(n1), Long.parseLong(n2), op1);
                long ret2 = cal(Long.parseLong(n3), Long.parseLong(n4), op2);
                if (ret1 < ret2) {// 뒤가 크면
                    e -= 2;
                    strings[e] = Long.toString(ret2);
                } else {
                    s += 2;
                    strings[s] = Long.toString(ret1);
                }
            } else {
                if (p(op1) < p(op2)) {// 뒤가 크면
                    e -= 2;
                    strings[e] = Long.toString(cal(Long.parseLong(n3), Long.parseLong(n4), op2));
                } else {
                    s += 2;
                    strings[s] = Long.toString(cal(Long.parseLong(n1), Long.parseLong(n2), op1));
                }
            }

        }
        System.out.println(strings[s]);

    }

    public int p(String op1) {
        switch (op1.charAt(0)) {
            case '-':
            case '+':
                return 0;
            default:
                return 1;

        }

    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        list = new ArrayList<>();
        int idx = 0;

        if (s.charAt(0) == '-') {
            while (++idx < s.length() && Character.isDigit(s.charAt(idx))) {
            }
            list.add(trim(s.substring(0, idx)));
        }
        while (idx < s.length()) {
            int start = idx;
            int end = start;

            if (!Character.isDigit(s.charAt(end))) {
                end++;
            } else {
                while (++end < s.length() && Character.isDigit(s.charAt(end))) {
                }

            }
            // System.out.println(start + " " + end);
            list.add(trim(s.substring(start, end)));
            idx = end;
        }
        // for (String string : list) {
        // System.out.println(string);
        // }
        solve();
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}