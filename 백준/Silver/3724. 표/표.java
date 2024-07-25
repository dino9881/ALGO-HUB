import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int T, M, N;
    static int[][] arr;

    public void solution()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++){
            BigDecimal max = new BigDecimal(1);
            int ans  = 0;
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            arr = new int[N][M];
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < M; i++){
                BigDecimal tmp = new BigDecimal(1);
                for (int j = 0; j < N; j++){
                    tmp = tmp.multiply(new BigDecimal(arr[j][i]));
                }
                // System.out.println(tmp + "  -> tmp");
                if (i == 0){
                    max = tmp;
                }else {
                    if (max.compareTo(tmp) <= 0){
                        max = tmp;
                        ans = i;
                    }
                }
            }
            sb.append(ans + 1).append("\n");
        }
        System.out.print(sb);        
    }

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}       