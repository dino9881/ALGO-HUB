import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * Main
 */
public class Main {

    static BigInteger N, K;
    public void solution()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = new BigInteger(st.nextToken());
        K = new BigInteger(st.nextToken());

        System.out.println(N.divide(K));
        System.out.println(N.remainder(K));
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
        
    }
}