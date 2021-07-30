import java.util.*;
import java.io.*;

public class 쿼드트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static char[][] map;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        sb = new StringBuilder();

        for(int i=0;i<N;i++){
            map[i] = br.readLine().toCharArray();
        }

        split(0,0,N-1,N-1,N);
        System.out.println(sb.toString());
    }

    static void split(int sy,int sx,int ey,int ex,int size){
        boolean allSame = true;
        char pivot = map[sy][sx];
        for(int i=sy;i<=ey && allSame;i++){
            for(int j=sx;j<=ex;j++){
                if(pivot != map[i][j]){
                    allSame = false;
                    break;
                }
            }
        }

        if(allSame){
            sb.append(pivot);
            return;
        }

        size /= 2;

        sb.append("(");
        // left top
        split(sy,sx,ey-size,ex-size,size);

        // right top
        split(sy,sx+size,ey-size,ex,size);

        // left bottom
        split(sy+size,sx,ey,ex-size,size);

        // right bottom
        split(sy+size,sx+size,ey,ex,size);

        sb.append(")");
    }
}
