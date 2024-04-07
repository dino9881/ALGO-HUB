import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * Main
 */
public class Main {

    static int N, K;
    static int[] arr;

    boolean isValid(int dist){
        int count = 1;
        int cur = arr[0];
        for (int i = 1; i < N;i++){
            if (cur + dist <= arr[i]){
                count++;
                cur = arr[i];
            }
        }
        // System.out.println(dist + " -> " + count);
        return count >= K;
    }

    public void solve(){
        int l = 0;
        int r = arr[N - 1] - arr[0] + 1;
        while (l < r){
            int m  = (l + r) / 2;
            if (!isValid(m)){
                r = m;
            }else{
                l = m + 1;
            }
        } 
        System.out.println(l - 1);
    }


    public void solution()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        solve();
        // isValid(1);
        // isValid(2);
        // isValid(3);
        // isValid(4);
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
        
    }
}