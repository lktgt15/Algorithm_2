# 1717번 집합의 표현
[문제 보러가기](https://www.acmicpc.net/problem/1717)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
두 원소가 같은 집합인지 판단하는 알고리즘이 있다. 대놓고 Union Find를 쓰라는 문제였다.

### 2. Union Find 코드

```java
static int find(int a){
    if(parent[a] < 0) return a;
    return parent[a] = find(parent[a]);
}

static void merge(int a,int b){
    a = find(a);
    b = find(b);
    if(a == b) return;
    parent[b] = a;
}
```
`find`는 a라는 원소의 부모를 찾아주는 함수다.  

`parent[]`는 index에 해당하는 원소의 부모를 가리킨다.  

`merge`는 a,b 원소를 하나의 집합으로 만드는 코드다.  

둘의 조상이 같을 경우에는 그냥 return하고 같지 않으면 `parent[b]=a` 로 b의 부모를 a로 바꾼다.  

### 3. 전체 코드
```java
import java.util.*;
import java.io.*;

public class 집합의표현 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n,m;
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];

        Arrays.fill(parent,-1);

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a,b,c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(a == 0){
                merge(b,c);
            }else{
                b = find(b);
                c = find(c);
                if(b != c) sb.append("NO\n");
                else sb.append("YES\n");
            }
        }
        System.out.print(sb);
    }

    static int find(int a){
        if(parent[a] < 0) return a;
        return parent[a] = find(parent[a]);
    }

    static void merge(int a,int b){
        a = find(a);
        b = find(b);
        if(a == b) return;
        parent[b] = a;
    }
}

```
맨 앞 숫자가 0인 경우 `merge(b,c)`, 1인 경우 `find(b), find(c)`를 하여 조상이 같다면 "YES", 아니면 "NO"를 출력하면 된다.  


## ✅ 후기
Union find 문제를 안 푼지 오래되서 코드가 생각이 안 날줄 알았는데 생각이 나서 신기했다.