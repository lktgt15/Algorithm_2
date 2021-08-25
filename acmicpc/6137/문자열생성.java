import java.util.*;
import java.io.*;

public class 문자열생성 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            char c = br.readLine().charAt(0);
            sb.append(c);
        }

        StringBuilder ansBuilder = new StringBuilder();
        int l = 0, r = N-1;
        int cnt = 0;
        while(l<r){
            cnt++;
            boolean flag = true;
            for(int i=0;l+i<=r-i && flag;i++){
                if(sb.charAt(l+i) < sb.charAt(r-i)){
                    flag = false;
                    ansBuilder.append(sb.charAt(l++));
                }else if(sb.charAt(l+i) > sb.charAt(r-i)){
                    flag = false;
                    ansBuilder.append(sb.charAt(r--));
                }
            }
            if(flag) {
                ansBuilder.append(sb.charAt(l++));
            }
            if(cnt%80 == 0) ansBuilder.append('\n');
        }
        ansBuilder.append(sb.charAt(l));
        System.out.println(ansBuilder);
    }
}
