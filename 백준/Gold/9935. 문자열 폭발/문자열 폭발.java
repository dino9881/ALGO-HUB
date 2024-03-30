import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    
    static int N, M;
    static String str, pattern;
    static Deque<Character> s, ans, tmp;
    
    
    public void solution()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        str = br.readLine();
        s = new ArrayDeque<>();
        ans = new ArrayDeque<>();
        tmp = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++){
            s.add(str.charAt(i));    
        }
        pattern = br.readLine();   
        int j = 0;
        while (!s.isEmpty()){
            char c = s.poll();
            // System.out.println("--------------");
            // System.out.println(c + " " + pattern.charAt(j));
            // System.out.println(s);
            // System.out.println(tmp);
            // System.out.println(ans);
            // System.out.println(j);
            if (c == pattern.charAt(j)){
                tmp.add(c);
                j++;
                if (j == pattern.length()){
                    tmp = new ArrayDeque<>();
                    j = 0;
                    int count = 0;
                    while (!ans.isEmpty() && ++count < pattern.length()){
                        s.addFirst(ans.pollLast());
                    }
                }
            }
            else{
                while (!tmp.isEmpty()){
                    ans.addLast(tmp.poll());
                }
                if (c == pattern.charAt(0)){
                    s.addFirst(c);
                    // for (int i = 0; i < j; i++){
                    //     ans.pollLast();
                    // }
                }
                else
                    ans.add(c);
                j = 0;
            }
        }
        while (!tmp.isEmpty()){
            ans.addLast(tmp.poll());
        }
        // System.out.println(ans);
        // System.out.println(tmp);
        // System.out.println(s);
        if (ans.isEmpty()){
            System.out.println("FRULA");
            return;
        }
            
        for (Character character : ans) {
            sb.append(character);
        }
        System.out.print(sb);
        
        
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}