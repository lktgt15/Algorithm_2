import java.io.*;
import java.util.*;

public class 체스판다시칠하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String[] board = new String[50];
    public static void main(String[] args) throws IOException{
        int ans = Integer.MAX_VALUE;
        int n,m;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i=0;i<n;i++) {
            board[i] = br.readLine();
        }

        for(int i=0;i<=n-8;i++){
            for(int j=0;j<=m-8;j++){
                ans = Math.min(ans,calc(i,j));
            }
        }
        System.out.print(ans);
    }

    static private int calc(int y,int x){
        int subans1 = 0, subans2 = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                char c = board[y+i].charAt(x+j);
                subans1 += ((i+j)%2)^(c == 'W' ? 1 : 0);
                subans2 += ((i+j)%2)^(c == 'B' ? 1 : 0);
            }
        }
        return Math.min(subans1,subans2);
    }
}
