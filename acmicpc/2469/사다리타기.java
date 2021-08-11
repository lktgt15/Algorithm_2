import java.util.*;
import java.io.*;

public class 사다리타기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int k,n,line;
    static int[] todown,toup;
    static char[][] map;
    static char[] ans;
    public static void main(String[] args) throws IOException{
        k = Integer.parseInt(br.readLine()); // col,start
        n = Integer.parseInt(br.readLine()); // row
        todown = new int[k];
        toup = new int[k];
        map = new char[n][k-1];
        ans = new char[k-1];

        String in = br.readLine();
        for(int i=0;i<in.length();i++){
            toup[i] = in.charAt(i)-'A';
            todown[i] = i;
        }

        for(int i=0;i<n;i++) {
            map[i] = br.readLine().toCharArray();
            if(map[i][0] == '?') line = i;
        }

        for(int i=0;i<line;i++){
            for(int j=0;j<k-1;j++){
                if(map[i][j] == '-') swap(todown,j,j+1);
            }
        }
        for(int i=n-1;i>line;i--){
            for(int j=0;j<k-1;j++){
                if(map[i][j] == '-') swap(toup,j,j+1);
            }
        }

        for(int i=0;i<k-1;i++){
            if(i>0 && ans[i-1] == '-'){
                ans[i] = '*';
                continue;
            }
            if(todown[i] != toup[i]){
                ans[i] = '-';
                swap(todown,i,i+1);
            }else{
                ans[i] = '*';
            }
        }

        for(int i=0;i<k;i++){
            if(todown[i] != toup[i]){
                for(int j=0;j<k-1;j++) ans[j] = 'x';
                break;
            }
        }

        for(int i=0;i<k-1;i++) System.out.print(ans[i]);

    }

    static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
