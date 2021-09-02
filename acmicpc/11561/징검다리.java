import java.util.*;
import java.io.*;

public class 징검다리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long N;
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) solve();
    }

    static void solve() throws IOException{
        N = Long.parseLong(br.readLine());
        long l,r,mid;
        l = 1;
        r = 10000000001L; // 10^10+1
        while(l+1<r){
            mid = (l+r)/2; // step
            if(f(mid) <= N) l = mid;
            else r = mid;
        }
        System.out.println(l);
    }

    static long f(long step){
        return (step*(step+1L))/2L;
    }
}
