import java.io.*;
import java.util.*;

public class K번째수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static long N,K;
    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        K = Long.parseLong(br.readLine());

        long l,r,mid;
        l = 0;
        r = N*N;
        while(l+1<r){
            mid = (l+r)/2; // mid보다 작거나 같은 수들의 개수를 센다
            long cnt = 0;
            for(int i=1;i<=N;i++){
                cnt += Math.min(N,mid/i); // 1 ... N행을 돌면서 mid보다 작거나 같은 수들의 개수를 센다
            }
            if(cnt < K){ // 현재 mid보다 작거나 같은 수의 개수 cnt가 K보다 작으면 현재 mid는 답이 될 수 없음
                l = mid;
            }else{ // 현재 mid보다 작거나 같은 수의 개수 cnt가 K보다 크거나 같으면 현재 mid는 답이 될 수 있음
                r = mid;
            }
        }
        System.out.println(r);
    }
}
