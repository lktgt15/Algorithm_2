# 14891번 톱니바퀴
[문제 보러가기](https://www.acmicpc.net/problem/1644)

## 🅰 설계
크게 신경쓸만한 부분이 없는 주어진대로 구현하면 되는 문제다.  

고려할만한 부분이라고 하면 톱니바퀴가 회전할 때, 현재 상태에 맞춰서 회전되며 다음 상태와 현재 상태가 뒤섞여서 계산될 수도 있는 부분이 있다.  

이 부분은 간단하게 현재 상태 배열과 다음 상태 배열을 분리하여 회전시키고, 회전이 끝난 후에는 현재 상태 배열을 다음 상태 배열로 바꿔주면 된다.

```java
import java.util.*;
import java.io.*;

public class 톱니바퀴 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] state = new int[4][8]; // N = 0, S = 1
    static int k;
    public static void main(String[] args) throws IOException {
        for(int i=0;i<4;i++){
            String in = br.readLine();
            for(int j=0;j<8;j++) state[i][j] = in.charAt(j)-'0';
        }
        k = Integer.parseInt(br.readLine());
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());

            rotate(num,dir);
        }

        int ans = 0;
        for(int i=0;i<4;i++){
            ans += state[i][0]*pow(2,i);
        }
        System.out.println(ans);
    }

    static int pow(int n,int k){
        int ret = 1;
        for(int i=0;i<k;i++) ret *= n;
        return ret;
    }

    static private void rotate(int num,int dir){
        int[][] nextState = new int[4][8];
        boolean chk[] = new boolean[4];
        chk[num] = true;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(num);
        q.offer(dir);
        while(!q.isEmpty()){
            int curnum = q.poll();
            int curdir = q.poll();
            for(int i=0;i<8;i++){
                nextState[curnum][i] = state[curnum][(i+(-1*curdir))&7];
            }
            // state[i][2] = Right, state[i][6] = Left
            if(curnum+1 < 4 && (state[curnum][2]^state[curnum+1][6]) == 1 && !chk[curnum+1]){
                q.offer(curnum+1);
                q.offer(curdir*-1);
                chk[curnum+1] = true;
            }
            if(curnum-1 >= 0 && ((state[curnum][6]^state[curnum-1][2]) == 1 && !chk[curnum-1])){
                q.offer(curnum-1);
                q.offer(curdir*-1);
                chk[curnum-1] = true;
            }
        }

        for(int i=0;i<4;i++){
            if(chk[i]){
                for(int j=0;j<8;j++) state[i][j] = nextState[i][j];
            }
        }
    }
}
```


## ✅ 후기
현재 상태에서 다음 상태로 변할 때 실수할 수 있는 부분이 종종 있다. 이걸 항상 신경 쓰도록 해야한다.