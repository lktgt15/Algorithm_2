import java.util.*;
import java.io.*;

public class 마인크래프트 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        int N,M,B;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int height[] = new int[257];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int cur = Integer.parseInt(st.nextToken());
                height[cur]++;
            }
        }

        int anstime = Integer.MAX_VALUE;
        int ansheight = 0;

        for(int i=256;i>=0;i--){
            int curB = B;
            int subtime = 0;
            for(int j=256;j>=0;j--){
                if(height[j] > 0){
                    if(j < i){
                        curB -= height[j]*(i-j);
                        subtime += height[j]*(i-j);
                    }else if(j > i){
                        curB += height[j]*(j-i);
                        subtime += height[j]*(j-i)*2;
                    }
                }
            }

            if(curB < 0) continue;

            if(anstime > subtime){
                anstime = subtime;
                ansheight = i;
            }
        }
        System.out.println(anstime + " " + ansheight);
    }
}
