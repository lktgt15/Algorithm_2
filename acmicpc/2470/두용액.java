import java.util.*;
import java.io.*;

public class 두용액 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static final int INF = 1000000000;
    static int[] arr;
    static int[] ans = {INF,INF};
    static int ansAbs = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

//        solveByBinarySearch();
        solveByTwoPointers();
    }

    static void solveByBinarySearch(){
        for(int i=0;i<N-1;i++){
            int pivot = -arr[i];
            int l,r,mid;
            l = i+1;
            r = N;
            while(l+1<r){
                mid = (l+r)/2;
                if(arr[mid] <= pivot) l = mid;
                else r = mid;
            }
            if(l != N-1 && Math.abs(arr[l+1]-pivot) < Math.abs(arr[l]-pivot)) l++;
            if(Math.abs(ans[0]+ans[1]) > Math.abs(arr[i]+arr[l])){
                ans[0] = arr[i];
                ans[1] = arr[l];
            }
        }
        System.out.println(ans[0]+" "+ans[1]);
    }

    static void solveByTwoPointers(){
        int l,r;
        l = 0;
        r = N-1;
        ans[0] = arr[l];
        ans[1] = arr[r];
        while(l+1<r){
            if(arr[l]+arr[r] <= 0) l++;
            else r--;
            if(Math.abs(ans[0]+ans[1]) > Math.abs(arr[l]+arr[r])){
                ans[0] = arr[l];
                ans[1] = arr[r];
            }
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
}
