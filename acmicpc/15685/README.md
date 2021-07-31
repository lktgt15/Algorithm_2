# 15685번 드래곤 커브
[문제 보러가기](https://www.acmicpc.net/problem/15685)

## 🅰 설계
### 1. 어떤 방법을 사용할 것인가?
- 드래곤 커브는 현재 Generation보다 작은 Generation들의 드래곤 커브를 반대로 돌면서 그 Generation의 Direction의 +1방향을 선택해서 이동하면 된다.
- 이는 모든 Generation을 돌면서 현재 Generation을 갱신하는 BruteForce를 사용하면 된다.

### 2. 전체코드

```java
import java.util.*;
import java.io.*;

public class 드래곤커브 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static boolean[][] map = new boolean[303][303];
    static int[] dy = {0,-1,0,1}, dx = {1,0,-1,0};
    static int[] curveGenDir;
    static int curveGenDirIdx;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x,y,d,g;
            x = Integer.parseInt(st.nextToken())+150;
            y = Integer.parseInt(st.nextToken())+150;
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            curveGenDir = new int[2000];
            map[x][y] = true;
            curveGenDir[0] = d;
            curveGenDirIdx = 0;
            y += dy[d];
            x += dx[d];
            map[x][y] = true;
            doCurve(x,y,1,g+1);
        }
        int answer = 0;
        for(int i=1;i<=300;i++) for(int j=1;j<=300;j++){
            if(map[i][j] == true && map[i+1][j] == true && map[i][j+1] == true && map[i+1][j+1] == true){
                answer++;
            }
        }

        System.out.println(answer);
    }

    static void doCurve(int x,int y,int curg,int endg){
        if(endg == curg) return;
        int d = 0;
        for(int i= curveGenDirIdx;i>=0;i--,curveGenDirIdx++){
            d = (curveGenDir[i]+1)%4;
            curveGenDir[curveGenDirIdx+1] = d;
            y += dy[d];
            x += dx[d];
            map[x][y] = true;
        }
        doCurve(x,y,curg+1,endg);
    }
}

```

## ✅ 후기
처음에 어떻게 풀어야 할지 고민했다. 규칙을 찾아보는 것도 중요하다는 것을 다시 깨달은것 같다.