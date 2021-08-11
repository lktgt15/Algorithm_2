# 2469번 사다리 타기
[문제 보러가기](https://www.acmicpc.net/problem/2469)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
사다리 문제는 가로 줄 하나에 상태 두개가 swap된다는 것을 알고 있으면 좋다.  

Wildcard가 되는 줄을 `line`이라고 하면,  

1. 0부터 `line`전 까지 map을 돌면서 status를 갱신한 것과
2. n-1부터 `line`전 까지 map을 돌면서 status를 갱신한 것  

이 두가지의 차이로 답을 구해나가면 된다.

### 2. status 구하는 코드

```java
String in = br.readLine();
for(int i=0;i<in.length();i++){
    toup[i] = in.charAt(i)-'A';
    todown[i] = i;
}

for(int i=0;i<n;i++) {
    map[i] = br.readLine().toCharArray();
    if(map[i][0] == '?') line = i;
}

// todown
for(int i=0;i<line;i++){
    for(int j=0;j<k-1;j++){
        if(map[i][j] == '-') swap(todown,j,j+1);
    }
}

// to up
for(int i=n-1;i>line;i--){
    for(int j=0;j<k-1;j++){
        if(map[i][j] == '-') swap(toup,j,j+1);
    }
}
```
`todown`은 0부터 시작해서 `line`까지 status를 계산하고,  

`toup`은 n-1부터 시작해서 `line`까지 status를 계산한다.  

그리고 `todown`과 `toup`이 같지 않으면 swap하고, 같으면 swap하지 않는다.  

### 3. 전체 코드

```java
import java.util.*;
import java.io.*;

public class 사다리타기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int k,n,line;
    static int[] todown,toup;
    static char[][] map;
    static char[] ans;
    public static void main(String[] args) throws IOException{
        k = Integer.parseInt(br.readLine()); // col,start
        n = Integer.parseInt(br.readLine()); // row
        todown = new int[k];
        toup = new int[k];
        map = new char[n][k-1];
        ans = new char[k-1];

        String in = br.readLine();
        for(int i=0;i<in.length();i++){
            toup[i] = in.charAt(i)-'A';
            todown[i] = i;
        }

        for(int i=0;i<n;i++) {
            map[i] = br.readLine().toCharArray();
            if(map[i][0] == '?') line = i;
        }

        for(int i=0;i<line;i++){
            for(int j=0;j<k-1;j++){
                if(map[i][j] == '-') swap(todown,j,j+1);
            }
        }
        for(int i=n-1;i>line;i--){
            for(int j=0;j<k-1;j++){
                if(map[i][j] == '-') swap(toup,j,j+1);
            }
        }

        for(int i=0;i<k-1;i++){
            if(i>0 && ans[i-1] == '-'){
                ans[i] = '*';
                continue;
            }
            if(todown[i] != toup[i]){
                ans[i] = '-';
                swap(todown,i,i+1);
            }else{
                ans[i] = '*';
            }
        }

        for(int i=0;i<k;i++){
            if(todown[i] != toup[i]){
                for(int j=0;j<k-1;j++) ans[j] = 'x';
                break;
            }
        }

        for(int i=0;i<k-1;i++) System.out.print(ans[i]);

    }

    static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

```

## ✅ 후기
처음에 모든 경우를 하는 Bruteforce 방법을 생각했는데, 시간제한도 조금 걸리고 구현도 잘못했는지 답이 안나왔다.  

실버문제중에 어려운 것들이 있는데 이것도 그런것중 하나였던 것 같았다.