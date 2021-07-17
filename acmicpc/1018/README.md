# 1018번 체스판 다시 칠하기
[문제 보러가기](https://www.acmicpc.net/problem/1018)

## 🅰 설계
입력받은 보드를 8\*8로 잘라서, WBWB... 형식이나 BWBW...형식의 체스판으로 만드는데 최소의 비용이 드는 지점을 찾으면 된다.  

이는 단순히 8\*8짜리 WBWB... , BWBW... 형식과 비교할 수도 있지만, W를 0로, B를 1으로 생각하면  

BWBW...와 맞는 체스판은 ((i+j)%2)^(c == 'W' ? 1 : 0)으로,  

WBWB...와 맞는 체스판은 ((i+j)%2)^(c == 'B' ? 1 : 0)으로 계산할 수 있다.  

이를 보드의 모든 8\*8 격자에 체크하면 된다.  

```java
import java.io.*;
import java.util.*;

public class 체스판다시칠하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String[] board = new String[50];
    public static void main(String[] args) throws IOException{
        int ans = Integer.MAX_VALUE;
        int n,m;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i=0;i<n;i++) {
            board[i] = br.readLine();
        }

        for(int i=0;i<=n-8;i++){
            for(int j=0;j<=m-8;j++){
                ans = Math.min(ans,calc(i,j));
            }
        }
        System.out.print(ans);
    }

    static private int calc(int y,int x){
        int subans1 = 0, subans2 = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                char c = board[y+i].charAt(x+j);
                subans1 += ((i+j)%2)^(c == 'W' ? 1 : 0);
                subans2 += ((i+j)%2)^(c == 'B' ? 1 : 0);
            }
        }
        return Math.min(subans1,subans2);
    }
}

```


## ✅ 후기
오랜만에 다시 비트를 사용했다. 그치만 단순한 솔루션이 먼저 생각나면 지체없이 단순하게 풀어야겠다는 생각을 계속 해야한다.