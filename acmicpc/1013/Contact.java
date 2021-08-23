import java.util.*;
import java.io.*;

public class Contact {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String Pattern = "(100+1+|01)+";
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++) solve();
    }

    static void solve() throws IOException{
        String in = br.readLine();
        if(in.matches(Pattern)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}
