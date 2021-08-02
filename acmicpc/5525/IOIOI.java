import java.util.*;
import java.io.*;

public class IOIOI {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,M;
    static String in;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        in = br.readLine();

        int streak = 0;
        int ans = 0;
        for(int i=0;i<M;i++){
            if(streak == 0){
                if(in.charAt(i) == 'I' && isValid(i+1) && in.charAt(i+1) == 'O' && isValid(i+2) && in.charAt(i+2) == 'I'){
                    streak = 1;
                    i+=2;
                    if(N == 1) ans++;
                }
            }else{
                if(in.charAt(i) == 'O' && isValid(i+1) && in.charAt(i+1) == 'I'){
                    streak++;
                    i++;
                    if(streak >= N) ans++;
                }else{
                    streak = 0;
                    i--;
                }
            }
        }
        System.out.println(ans);
    }

    static boolean isValid(int i){
        return i<M;
    }
}
