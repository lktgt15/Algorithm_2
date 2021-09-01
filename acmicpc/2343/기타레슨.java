import java.util.*;
import java.io.*;

public class 기타레슨 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 1000000000;
        int mid = 0;
        while(l+1<r){
            mid = (l+r)/2;
            if(f(mid)) r = mid;
            else l = mid;
        }
        System.out.println(r);
    }

    static boolean f(int max){
        int sum = 0;
        int cnt = 1;
        for(int i=0;i<N;i++){
            if(arr[i] > max) return false;
            if(arr[i]+sum > max){
                sum = 0;
                cnt++;
            }
            sum += arr[i];
        }
        return cnt <= M;
    }
}
