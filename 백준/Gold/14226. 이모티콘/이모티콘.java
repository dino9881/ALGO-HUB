import java.util.*;
import java.io.*;


/**
 * Main
 */
public class Main {
    static int N;
    static boolean memo[][] = new boolean[1000 + 1][1000 + 1];

    /**
     *  Data
     */
    public class  Data {
        
        int cur, clip, count;

        Data(int cur, int clip, int count){
            this.cur = cur;
            this.clip = clip;
            this.count = count;
        }
        
    }
  
    public void solution()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Queue<Data> q = new ArrayDeque<>();

        q.add(new Data(1, 0, 0));
        while (!q.isEmpty()) {
            Data data = q.poll();

            int cur = data.cur;
            int clip = data.clip;
            int count = data.count;
            memo[cur][clip] = true;
            if (cur == N){
                System.out.println(count);
                return;
            }
            if (data.clip != 0 && cur + clip <= 1000 && !memo[cur + clip][clip]){
                q.add(new Data(cur + clip, clip, count + 1));
            }
            if (cur != clip && !memo[cur][cur]){
                q.add(new Data(cur, cur, count + 1));
            }
            if (cur - 1 >= 0 && !memo[cur - 1][clip]){
                q.add(new Data(cur - 1, clip, count + 1));
            }
        }
        
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}