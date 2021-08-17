import java.util.*;
import java.io.*;

public class DNA발견 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] dp = new int[2][1000001];
    static char[] in;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        in = br.readLine().toCharArray();


        Arrays.fill(dp[0],-1);
        Arrays.fill(dp[1],-1);

        if(in[0] == 'A') {
            dp[0][0] = 0;
            dp[1][0] = 1;
        }else{
            dp[0][0] = 1;
            dp[1][0] = 0;
        }

        System.out.println(f(N-1,0));
    }

    //
    static int f(int idx,int c){
        if(dp[c][idx] != -1) return dp[c][idx];

        if(in[idx]-'A' == c){
            int a = f(idx-1,c);
            int b = f(idx-1,c^1)+1;
            dp[c][idx] = a <= b ? a : b;
        }else{
            int a = f(idx-1,c)+1;
            int b = f(idx-1,c^1)+1;
            dp[c][idx] = a <= b ? a : b;
        }
        return dp[c][idx];
    }
}
