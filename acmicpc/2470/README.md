# 2470번 두 용액
[문제 보러가기](https://www.acmicpc.net/problem/2470)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
- 바로 떠오르는 방법은 이중 포문을 이용하여 두 원소를 선택하는 방법으로 O(N^2)안에 답을 찾을 수 있다.
- 그러나 N의 최대 제한이 10만이기 때문에 O(N^2)의 방법은 불가능하다.
- 다음으로 두 가지의 방법을 더 떠올릴 수 있다.
1. 이분탐색
   - 1부터 N까지 모든 원소에 대해서 해당 원소를 정해놓고, 그 원소와의 합이 0에 가까운 다른 원소를 찾을 때 정렬이 되어있는 배열에서는 이분탐색을 사용할 수 있다.
2. 투포인터
   - l=0, r=N-1부터 시작하여 두 위치에 있는 원소의 합이 0보다 작거나 같으면 l의 인덱스값을 올리고, 반대의 경우 r의 인덱스값을 줄이면서 답을 갱신하면 답을 찾을 수 있다.
    
### 2. 이분탐색 코드
```java
    static void solveByBinarySearch(){
        for(int i=0;i<N-1;i++){
            int pivot = -arr[i];
            int l,r,mid;
            l = i+1;
            r = N;
            while(l+1<r){
                mid = (l+r)/2;
                if(arr[mid] <= pivot) l = mid;
                else r = mid;
            }
            if(l != N-1 && Math.abs(arr[l+1]-pivot) < Math.abs(arr[l]-pivot)) l++;
            if(Math.abs(ans[0]+ans[1]) > Math.abs(arr[i]+arr[l])){
                ans[0] = arr[i];
                ans[1] = arr[l];
            }
        }
        System.out.println(ans[0]+" "+ans[1]);
    }
```
- pivot은 현재 i의 원소값을 0으로 만들 수 있는 값이 되며 이 pivot값에 가장 가까운 값을 찾으면 i에 있는 원소와 합하였을 때 0에 가장 가깝게 된다.

### 3. 투포인터 코드
```java
static void solveByTwoPointers(){
    int l,r;
    l = 0;
    r = N-1;
    ans[0] = arr[l];
    ans[1] = arr[r];
    while(l+1<r){
        if(arr[l]+arr[r] <= 0) l++;
        else r--;
        if(Math.abs(ans[0]+ans[1]) > Math.abs(arr[l]+arr[r])){
            ans[0] = arr[l];
            ans[1] = arr[r];
        }
    }
    System.out.println(ans[0] + " " + ans[1]);
}
```
- 위에서 설명한 대로 l과 r값을 한단계씩 조절하며 그 때마다 답을 갱신해준다.

### 4. 전체 코드

```java
import java.util.*;
import java.io.*;

public class 두용액 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static final int INF = 1000000000;
    static int[] arr;
    static int[] ans = {INF,INF};
    static int ansAbs = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

//        solveByBinarySearch();
        solveByTwoPointers();
    }

    static void solveByBinarySearch(){
        for(int i=0;i<N-1;i++){
            int pivot = -arr[i];
            int l,r,mid;
            l = i+1;
            r = N;
            while(l+1<r){
                mid = (l+r)/2;
                if(arr[mid] <= pivot) l = mid;
                else r = mid;
            }
            if(l != N-1 && Math.abs(arr[l+1]-pivot) < Math.abs(arr[l]-pivot)) l++;
            if(Math.abs(ans[0]+ans[1]) > Math.abs(arr[i]+arr[l])){
                ans[0] = arr[i];
                ans[1] = arr[l];
            }
        }
        System.out.println(ans[0]+" "+ans[1]);
    }

    static void solveByTwoPointers(){
        int l,r;
        l = 0;
        r = N-1;
        ans[0] = arr[l];
        ans[1] = arr[r];
        while(l+1<r){
            if(arr[l]+arr[r] <= 0) l++;
            else r--;
            if(Math.abs(ans[0]+ans[1]) > Math.abs(arr[l]+arr[r])){
                ans[0] = arr[l];
                ans[1] = arr[r];
            }
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
}

```

## ✅ 후기
예전에 이분탐색으로 풀어봤던 문제인데, 오히려 투포인터 방법을 생각을 잘 못했다. 저렇게 해서 예외없이 답을 찾을 수 있나? 라는 생각을 계속 했던것 같다.