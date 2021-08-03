import java.util.*;
import java.io.*;

public class 로또 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] baseArr = new int[13];
    static int[] ansArr = new int[6];
    static int k;
    public static void main(String[] args) throws IOException{
        while(true){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0) break;

            for(int i=0;i<k;i++){
                baseArr[i] = Integer.parseInt(st.nextToken());
            }

            recursive(0,0);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void recursive(int start,int idx){
        if(idx == 6){
            for(int i=0;i<6;i++){
                sb.append(ansArr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=start;i<k;i++){
            ansArr[idx] = baseArr[i];
            recursive(i+1,idx+1);
        }
    }
}
