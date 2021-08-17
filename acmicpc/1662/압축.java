import java.util.*;
import java.io.*;

public class 압축 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char[] in;
    public static void main(String[] args) throws IOException{
        in = ( "1("+br.readLine()+"))" ).toCharArray();

        System.out.println(f(0));
    }

    static int f(int idx){
        int ret = 0;
        Stack<Character> st = new Stack<>();
        while(true){
            char c = in[idx];
            switch (c){
                case '(':
                    int k = st.pop()-'0';
                    ret += k*f(idx+1);
                    int cnt = 1;
                    while(cnt > 0) {
                        idx++;
                        if(in[idx] == '(')cnt++;
                        else if(in[idx] == ')')cnt--;
                    }
                    break;
                case ')':
                    return ret+st.size();
                default:
                    st.push(c);
                    break;
            }
            idx++;
        }
    }
}
