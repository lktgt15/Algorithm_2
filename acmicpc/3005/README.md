# 3005번 크로스워드 퍼즐 쳐다보기
[문제 보러가기](https://www.acmicpc.net/problem/3005)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
R과 C가 20이고 BruteForce로 모든 행,열을 두 번씩 돌아도 O(R\*C\*2) 로 시간제한은 생각하지 않아도 된다.  

모든 행을 왼쪽->오른쪽으로, 모든 열을 위->아래로 돌면서 길이가 2이상인 모든 문자를 탐색하면 된다.

### 2. 특정 행,열의 처음에서 시작하는 탐색 코드

```java
static int[] dy={1,0},dx={0,1};

// ...

static String getLongestWord(int y,int x,int dir){
    String ret = "zzzzzzzzzzzzzzzzzzzzzzzzzzz";
    StringBuilder subret = new StringBuilder();
    while(y<R && x<C){
        if(map[y][x] == '#'){
            if(subret.length() > 1 && ret.compareTo(subret.toString()) > 0){
                ret = subret.toString();
            }
            subret.setLength(0);
        }else{
            subret.append(map[y][x]);
        }
        y += dy[dir];
        x += dx[dir];
    }
    if(subret.length() > 1 && ret.compareTo(subret.toString()) > 0){
        ret = subret.toString();
    }

    return ret;
}
```
`dir`은 0 = 아래, 1 = 오른쪽 을 의미한다.  

특정 행 또는 열의 처음부터 시작하여 map의 바깥으로 나가기 전까지 탐색한다.  

만약 `#`이 있으면 지금까지 합친 문자들을 `ret`값과 비교하여 사전순(compareTo의 return값이 0보다 크면 비교 문자열이 사전순으로 앞선다)으로 비교하여 `ret`를 갱신해준다.  

while문을 빠져나오기 전의 마지막 문자는 고려하지 못하므로 while문을 빠져나와서도 한번 더 비교해주어야 한다.  

### 3. 전체 코드

```java
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R,C;
    static char[][] map;
    static int[] dy={1,0},dx={0,1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0;i<R;i++){
            String in = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = in.charAt(j);
            }
        }

        String ans = "zzzzzzzzzzzzzzzzzzzzzzzzzzz";
        for(int i=0;i<R;i++){
            String subans = getLongestWord(i,0,1);

            if(ans.compareTo(subans) > 0){
                ans = subans;
            }
        }
        for(int i=0;i<C;i++){
            String subans = getLongestWord(0,i,0);

            if(ans.compareTo(subans) > 0){
                ans = subans;
            }
        }
        System.out.println(ans);
    }

    static String getLongestWord(int y,int x,int dir){
        String ret = "zzzzzzzzzzzzzzzzzzzzzzzzzzz";
        StringBuilder subret = new StringBuilder();
        while(y<R && x<C){
            if(map[y][x] == '#'){
                if(subret.length() > 1 && ret.compareTo(subret.toString()) > 0){
                    ret = subret.toString();
                }
                subret.setLength(0);
            }else{
                subret.append(map[y][x]);
            }
            y += dy[dir];
            x += dx[dir];
        }
        if(subret.length() > 1 && ret.compareTo(subret.toString()) > 0){
            ret = subret.toString();
        }

        return ret;
    }
}

```

각 행,열의 처음부터 돌면서 `getLongestWord`의 return값을 계속 비교해주면서 답을 갱신해주면 된다.

## ✅ 후기
이 문제를 풀면서 고생한 부분이 2가지 있다.  

1. String의 compareTo 메소드 리턴값의 의미를 정확히 알지 못했다. original.compareTo(compareString)이라고 하면, 이 return값이 0이면 완전히 같은 string이고, 0 < return 이면 compareString이 사전순으로 앞선다. 0 > return이면 그 반대이다.  
2. String의 초기화를 어떤식으로 해야할까 생각했다. 공백의 경우 어떤 문자열보다 항상 사전순으로 앞서기 때문에 공백으로 초기화하면 안된다. 알파벳 대문자가 소문자보다 ASCII값으로 앞서므로 소문자 z로 초기화해주어야 답이 갱신이 되었다.  

BruteForce 문제가 은근히 까다로운 조건들을 공부하기 좋은것 같다.