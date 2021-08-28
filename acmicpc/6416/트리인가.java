import java.util.*;
import java.io.*;

public class 트리인가 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        // 1. root(들어오는 간선이 하나도 없는 노드)가 2개 이상 있다.
        // 2. 간선이 두 개 이상 들어오는 노드가 존재한다.
        // 3. 루트에서 모든 노드로 갈 수 없다.

        int cnt = 1;
        while(true){
            if(solve(cnt++)) break;
            if(br.readLine().length() > 2) break;
        }
    }

    static boolean solve(int cnt) throws IOException{
        Map<Integer,ArrayList<Integer>> path = new HashMap<>();
        Map<Integer,Integer> indegree = new HashMap<>();
        Map<Integer,Boolean> chk = new HashMap<>();
        Set<Integer> nodes = new HashSet<>();

        while(true){
            st = new StringTokenizer(br.readLine());
            int u = 0,v = 0;
            while(st.hasMoreTokens()) {
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                if(u == 0 && v == 0) {
                    break;
                }
                ArrayList<Integer> val = path.getOrDefault(u,new ArrayList<>());
                val.add(v);
                path.put(u,val);

                indegree.put(v,indegree.getOrDefault(v,0)+1);

                nodes.add(u);
                nodes.add(v);
            }
            if(u == 0 && v == 0) break;
            if(u == -1 && v == -1) return true;
        }
        int root = -1;
        if(nodes.size() == 0){
            print1(cnt);
            return false;
        }

        // 1.
        for(int key : nodes){
            int curindegree = indegree.getOrDefault(key,0);
            if(curindegree == 0){
                if(root != -1){
                    print2(cnt);
                    return false;
                }
                root = key;
            }else if(curindegree > 1){
                print2(cnt);
                return false;
            }
        }
        if(root == -1){
            print2(cnt);
            return false;
        }

        // 3.
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(root);
        chk.put(root,true);
        int nodecnt = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            nodecnt++;

            if(!path.containsKey(cur)) continue;
            for(int nxt : path.get(cur)){
                if(chk.containsKey(nxt)){
                    print2(cnt);
                    return false;
                }
                q.offer(nxt);
                chk.put(nxt,true);
            }
        }

        if(nodecnt != nodes.size()){
            print2(cnt);
            return false;
        }
        print1(cnt);
        return false;
    }

    static void print1(int cnt){
        System.out.printf("Case %d is a tree.\n",cnt);
    }

    static void print2(int cnt){
        System.out.printf("Case %d is not a tree.\n",cnt);
    }
}
