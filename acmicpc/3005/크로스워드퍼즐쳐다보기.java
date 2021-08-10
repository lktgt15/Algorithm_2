import java.util.*;
import java.io.*;

public class 크로스워드퍼즐쳐다보기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R,C;
    static char[][] map;
    static int[] dy={1,0},dx={0,1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0;i<R;i++){
            String in = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = in.charAt(j);
            }
        }

        String ans = "";
        for(int i=0;i<R;i++){
            String subans = getLongestWord(i,0,1);

            if(ans.compareTo(subans) > 0){
                ans = subans;
            }
        }
        for(int i=0;i<C;i++){
            String subans = getLongestWord(0,i,0);

            if(ans.compareTo(subans) > 0){
                ans = subans;
            }
        }
        System.out.println(ans);
    }

    static String getLongestWord(int y,int x,int dir){
        String ret = "";
        StringBuilder subret = new StringBuilder();
        while(y<R && x<C){
            if(map[y][x] == '#'){
                if(subret.length() > 1 && ret.compareTo(subret.toString()) > 0){
                    ret = subret.toString();
                }
                subret.setLength(0);
            }else{
                subret.append(map[y][x]);
            }
            y += dy[dir];
            x += dx[dir];
        }
        if(subret.length() > 1 && ret.compareTo(subret.toString()) > 0){
            ret = subret.toString();
        }

        return ret;
    }
}
