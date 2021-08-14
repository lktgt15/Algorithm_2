# 15558번 점프 게임
[문제 보러가기](https://www.acmicpc.net/problem/15558)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
같은 라인의 pos+1,pos-1,다른 라인의 pos+k로 이동하는 3가지의 방법이 있다.  

다른 라인으로 이동하기 위해 pos-1인 뒤로 이동해보는 행동도 필요하며, 완전탐색의 방법중 하나를 선택해야겠다는 생각이 든다.  

완전탐색의 방법으로 BFS를 사용하기로 했다.  

### 2. 전체 코드

```java
import java.util.*;
import java.io.*;

public class 점프게임 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n,k;
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] map = new int[2][n+k+1];
        for(int i=0;i<2;i++){
            String in = br.readLine();
            for(int j=0;j<n;j++){
                map[i][j] = in.charAt(j)-'0';
            }
            for(int j=n;j<=k;j++){
                map[i][j] = 1;
            }
        }

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0,0));
        boolean[][] chk = new boolean[2][n+k+1];

        int answer = 0;
        chk[0][0] = true;
        int time = 0;
        while(!q.isEmpty()){

            int size = q.size();
            for(int i=0;i<size;i++){

                Point cur = q.poll();

                if(cur.pos < time) continue;

                if(cur.pos >= n){
                    answer = 1;
                    break;
                }

                // 1
                if((!chk[cur.line^1][cur.pos+k] && map[cur.line^1][cur.pos+k] == 1) || cur.pos+k >= n){
                    chk[cur.line^1][cur.pos+k] = true;
                    q.offer(new Point(cur.line^1,cur.pos+k));
                }

                // 2
                if(cur.pos-1 >= 0 && !chk[cur.line][cur.pos-1] && map[cur.line][cur.pos-1] == 1){
                    chk[cur.line][cur.pos-1] = true;
                    q.offer(new Point(cur.line,cur.pos-1));
                }

                // 3
                if(!chk[cur.line][cur.pos+1] && map[cur.line][cur.pos+1] == 1){
                    chk[cur.line][cur.pos+1] = true;
                    q.offer(new Point(cur.line, cur.pos+1));
                }
            }
            time++;
        }
        System.out.println(answer);
    }

    static class Point{
        int line;
        int pos;

        public Point(){
        }

        public Point(int line,int pos){
            this.line = line;
            this.pos = pos;
        }
    }
}

```
Queue를 이용한 BFS를 하며, 특이한 점으로는 시간이 지나감에 따라 접근할 수 없는 칸이 있으므로 `time`변수를 추가하여 체크해주었다.


## ✅ 후기
'그냥 BFS하면 풀리겠는데?'라고 생각했는데, 생각보다 체크할 것이 많았다. 