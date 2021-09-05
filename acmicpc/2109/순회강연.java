import java.util.*;
import java.io.*;

public class 순회강연 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Class[] classes = new Class[n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int p,d;
            p = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            classes[i] = new Class(p,d);
        }

        Arrays.sort(classes, Comparator.comparingInt(c -> c.d));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int ans = 0;
        for(int i=0;i<n;i++){
            pq.add(classes[i].p);
            while(classes[i].d < pq.size()){
                pq.poll();
            }
        }
        while(!pq.isEmpty()){
            ans += pq.poll();
        }

        System.out.println(ans);
    }

    static class Class{
        int p,d;

        public Class(){

        }
        public Class(int p,int d){
            this.p = p;
            this.d = d;
        }
    }
}
