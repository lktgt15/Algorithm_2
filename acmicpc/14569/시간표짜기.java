import java.io.*;
import java.util.*;

public class 시간표짜기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,M,k;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        long[] lectures = new long[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            for(int j=0;j<k;j++){
                lectures[i] |= 1L << Long.parseLong(st.nextToken());
            }
        }
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            long student = 0;
            int subans = 0;
            for(int j=0;j<k;j++){
                student |= 1L << Long.parseLong(st.nextToken());
            }
            for(int j=0;j<N;j++){
                if((student & lectures[j]) == lectures[j]){
                    subans++;
                }
            }
            sb.append(subans).append("\n");
        }
        System.out.print(sb.toString());
    }
}
