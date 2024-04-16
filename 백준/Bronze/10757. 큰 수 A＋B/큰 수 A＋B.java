import java.util.*;
import java.io.*;
import java.math.BigInteger;


/**
 * Main
 */
public class Main {
    static BigInteger A, B;
  
    public void solution()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       A = new BigInteger(st.nextToken());
       B = new BigInteger(st.nextToken());
    
       System.out.println(A.add(B));
        
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}