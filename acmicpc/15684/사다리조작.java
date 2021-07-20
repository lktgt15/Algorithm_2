import java.util.*;
import java.io.*;

public class 사다리조작 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, H, ans;
    static boolean[][] line;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        line = new boolean[H+1][N+1];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a, b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            line[a-1][b-1] = true;
        }

        ans = 4;
        solve(0,0,0);
        System.out.print(ans == 4 ? -1 : ans);
    }

    static void solve(int h, int n, int k) {
        if(ans > k && checkComplete()) {
            ans = k;
            return;
        }

        if(k == 3) return;
        for(int i=h+1;i<H;i++) for(int j=n;j<N-1;j++) {
            if(isValidLine(i, j)) {
                line[i][j] = true;
                solve(i, j+2, k+1);
                line[i][j] = false;
            }
            n = 0;
        }
    }

    static boolean isValidLine(int i, int j) {
        if(j>0 && line[i][j-1]) return false;
        if(line[i][j+1]) return false;
        return !line[i][j];
    }

    static boolean checkComplete() {
        for(int i=0;i<N;i++){
            int curHorizontalLine = i;
            for(int curVerticalLine=0;curVerticalLine<H;curVerticalLine++){
                if(line[curVerticalLine][curHorizontalLine]) {
                    curHorizontalLine++;
                }else if(curHorizontalLine>0 && line[curVerticalLine][curHorizontalLine-1]){
                    curHorizontalLine--;
                }
            }
            if(curHorizontalLine != i) return false;
        }
        return true;
    }
}
