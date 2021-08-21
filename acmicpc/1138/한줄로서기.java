import java.io.*;
import java.util.*;

public class 한줄로서기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] arr,ans;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        ans = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        f(0,0);
    }

    static boolean f(int cnt,int mask){
        if(cnt == n) {
            for(int i=0;i<n;i++){
                int subcnt = 0;
                for(int j=0;j<i;j++){
                    if(ans[i] < ans[j]) subcnt++;
                }
                if(subcnt != arr[ans[i]]) return false;
            }
            print();
            return true;
        }

        for(int i=0;i<n;i++){
            if((mask&1<<i) == 0){
                ans[cnt] = i;
                if(f(cnt+1,mask|1<<i)) return true;
            }
        }
        return false;
    }

    static void print(){
        for(int i=0;i<n;i++) System.out.print(ans[i]+1+" ");
    }
}
