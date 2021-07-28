import java.util.*;
import java.io.*;

public class 회의실배정 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int s,e;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            meetings[i] = new Meeting(s,e);
        }
        Arrays.sort(meetings);
        int ans = 0;
        int lastEnd = -1;

        for(int i=0;i<N;i++){
            if(lastEnd <= meetings[i].s){
                lastEnd = meetings[i].e;
                ans++;
            }
        }
        System.out.println(ans);
    }

    static class Meeting implements Comparable<Meeting>{
        int s,e;
        public Meeting(int s,int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.e == o.e)
                return Integer.compare(this.s,o.s);
            return Integer.compare(this.e,o.e);
        }
    }
}
