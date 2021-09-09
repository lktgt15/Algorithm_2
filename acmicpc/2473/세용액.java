import java.util.*;
import java.io.*;

public class 세용액 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static final long MAX = 1000000000;
    static int[] arr;
    static long[] ans={MAX,MAX,MAX};
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        for(int i=0;i<N-2;i++){
            int l,r;
            long a,b,c;
            a = arr[i];
            l = i+1;
            r = N-1;
            while(l<r){
                long cursum = a+arr[l]+arr[r];
                if(Math.abs(cursum) < Math.abs(ans[0]+ans[1]+ans[2])){
                    ans[0] = a;
                    ans[1] = arr[l];
                    ans[2] = arr[r];
                }
                if(cursum >= 0){
                    r--;
                }else{
                    l++;
                }
            }
        }
        System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
    }
}
