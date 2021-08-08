import java.io.*;
import java.util.*;

public class ACM호텔 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int T,H,W,N;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while(T-- > 0) solve();
    }

    static void solve() throws IOException{
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
//        solveByBruteforce();
        solveByMath();
    }

    static void solveByBruteforce() throws IOException{
        int cnt = 0;
        for(int j=1;j<=W;j++){
            for(int i=1;i<=H;i++){
                cnt++;

                if(cnt == N){
                    if(j >= 10){
                        System.out.println(i+""+j);
                    }else{
                        System.out.println(i+"0"+j);
                    }
                    return;
                }
            }
        }
    }

    static void solveByMath(){
        int hm = N/H+1;
        int hr = N%H;
        hm = hr == 0 ? hm-1 : hm;
        hr = hr == 0 ? H : hr;
        if(hm >= 10) System.out.println(hr+""+hm);
        else System.out.println(hr+"0"+hm);
    }
}
