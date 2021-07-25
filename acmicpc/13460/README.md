# 13460번 구슬탈출 2
[문제 보러가기](https://www.acmicpc.net/problem/13460)

## 🅰 설계

### 1. 어떤 방법을 쓸 것인가?

- 4방향으로 구슬을 한번 씩 굴려보는 행동을 10번 까지 반복해서 해볼 수 있다. 보드가 최대 10x10이므로 구슬을 한 방향으로 굴릴 때 최대 8번 까지 이동한다. 시간복잡도는 약 O(8\*2)\*O(4^10) = P(16,777,216) 가 된다. 중간에 많은 연산이 생략되어 있지만 이정도면 문제의 시간제한 내에 해결할 수 있다.  

### 2. solve()

```java
static void solve(int trial){
    if(trial >= ans) return;
    int prevRy = Ry, prevRx = Rx, prevBy = By, prevBx = Bx;

    for(int dir=0;dir<4;dir++){
        for(int type=0;type<2;type++){ // 0 == R , 1 == B
            move(dir,type);
        }
        if(By == Oy && Bx == Ox) {
            Ry = prevRy; Rx = prevRx;
            By = prevBy; Bx = prevBx;
            continue;
        }else if(Ry == Oy && Rx == Ox){
            ans = Math.min(trial,ans);
            Ry = prevRy; Rx = prevRx;
            By = prevBy; Bx = prevBx;
            return;
        }
        if(Ry == By && Rx == Bx){
            int Rdist = Math.abs(Ry-prevRy)+Math.abs(Rx-prevRx);
            int Bdist = Math.abs(By-prevBy)+Math.abs(Bx-prevBx);
            if(Rdist <= Bdist){
                By -= dy[dir];
                Bx -= dx[dir];
            }else{
                Ry -= dy[dir];
                Rx -= dx[dir];
            }
        }
        solve(trial+1);
        Ry = prevRy; Rx = prevRx;
        By = prevBy; Bx = prevBx;
    }
}
```
- `solve(trial)`는 재귀로 구슬을 4방향으로 움직이면서 답을 찾는 메소드다.  
- By,Bx가 O에 닿으면 이번 행동은 절대 답이 될 수 없으므로 `continue`로 다시 시도한다.
- Ry,Rx가 O에 닿으면 답이 될 수 있으므로 ans를 업데이트한다. 이번 시도 이상으로는 해볼 필요가 없으므로 return한다.
- Ry,Rx와 By,Bx가 같으면 dist를 계산하여 더 적은 거리를 이동한 구슬을 두고 더 많은 거리를 이동한 구슬은 반대방향으로 이동시킨다.

### 3. move()
```java
static void move(int dir,int type){
    if(type == 0) moveR(dir);
    else moveB(dir);
}

static void moveR(int dir){
    int nxtRy = Ry+dy[dir], nxtRx = Rx+dx[dir];
    while(isValid(nxtRy,nxtRx)){
        Ry = nxtRy;
        Rx = nxtRx;
        if(Ry == Oy && Rx == Ox) break;
        nxtRy += dy[dir];
        nxtRx += dx[dir];
    }
}

static void moveB(int dir){
    int nxtBy = By+dy[dir], nxtBx = Bx+dx[dir];
    while(isValid(nxtBy,nxtBx)){
        By = nxtBy;
        Bx = nxtBx;
        if(By == Oy && Bx == Ox) break;
        nxtBy += dy[dir];
        nxtBx += dx[dir];
    }
}
```
- `move(dir,type)`은 빨간 구슬, 파란 구슬을 dir방향으로 움직이는 함수이다.
- 다음 이동할 지점이 `'.'`이면 움직인다. 그런데 그 지점이 구멍이면 바로 그만둔다.
- 이렇게 움직여서 움직일 수 없을 때까지 움직이면 된다.

### 4. 전체 코드

```java
import java.util.*;
import java.io.*;

public class 구슬탈출2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,M,ans = 11;
    static int[] dy={1,-1,0,0}, dx={0,0,1,-1};
    static char[][] map;
    static int Ry,Rx,By,Bx,Oy,Ox;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0;i<N;i++){
            String in = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = in.charAt(j);
                if(map[i][j] == 'O') {map[i][j] = '.'; Oy = i; Ox = j;}
                else if(map[i][j] == 'R') {map[i][j] = '.'; Ry = i; Rx = j;}
                else if(map[i][j] == 'B')  {map[i][j] = '.'; By = i; Bx = j;}
            }
        }

        solve(1);
        System.out.println(ans == 11 ? -1 : ans);
    }

    static void solve(int trial){
        if(trial >= ans) return;
        int prevRy = Ry, prevRx = Rx, prevBy = By, prevBx = Bx;

        for(int dir=0;dir<4;dir++){
            for(int type=0;type<2;type++){ // 0 == R , 1 == B
                move(dir,type);
            }
            if(By == Oy && Bx == Ox) {
                Ry = prevRy; Rx = prevRx;
                By = prevBy; Bx = prevBx;
                continue;
            }else if(Ry == Oy && Rx == Ox){
                ans = Math.min(trial,ans);
                Ry = prevRy; Rx = prevRx;
                By = prevBy; Bx = prevBx;
                return;
            }
            if(Ry == By && Rx == Bx){
                int Rdist = Math.abs(Ry-prevRy)+Math.abs(Rx-prevRx);
                int Bdist = Math.abs(By-prevBy)+Math.abs(Bx-prevBx);
                if(Rdist <= Bdist){
                    By -= dy[dir];
                    Bx -= dx[dir];
                }else{
                    Ry -= dy[dir];
                    Rx -= dx[dir];
                }
            }
            solve(trial+1);
            Ry = prevRy; Rx = prevRx;
            By = prevBy; Bx = prevBx;
        }
    }

    static void move(int dir,int type){
        if(type == 0) moveR(dir);
        else moveB(dir);
    }

    static void moveR(int dir){
        int nxtRy = Ry+dy[dir], nxtRx = Rx+dx[dir];
        while(isValid(nxtRy,nxtRx)){
            Ry = nxtRy;
            Rx = nxtRx;
            if(Ry == Oy && Rx == Ox) break;
            nxtRy += dy[dir];
            nxtRx += dx[dir];
        }
    }

    static void moveB(int dir){
        int nxtBy = By+dy[dir], nxtBx = Bx+dx[dir];
        while(isValid(nxtBy,nxtBx)){
            By = nxtBy;
            Bx = nxtBx;
            if(By == Oy && Bx == Ox) break;
            nxtBy += dy[dir];
            nxtBx += dx[dir];
        }
    }

    static boolean isValid(int y,int x){
        return map[y][x] == '.';
    }
}
```


## ✅ 후기
세세한 부분을 신경써야 하는 좋은 구현 연습 문제였던것 같다. 구슬이 겹칠 때 처리해주는 부분이 처음에는 생각하기 힘들었었다.
