# 1918번 소수의 연속합
[문제 보러가기](https://www.acmicpc.net/problem/1644)

## 🅰 설계

### 소수 판별
```java
// build primes array
static void init(){
    Arrays.fill(isPrime,true);
    for(long i=2;i<=N;i++){
        if(isPrime[(int) i]){
            primes.add((int)i);
            for(long j=i*i;j<=N;j+=i){
                isPrime[(int)j] = false;
            }
        }
    }
}
```
- *에라토스테네스의 체*를 사용한다. 소수를 여러번 재사용 하여야 한다면 여러번 소수를 구하지 않고 이렇게 소수 리스트를 먼저 찾아놓고 시작하는게 좋다.  

- 처음 isPrime 배열을 true로 초기화하고, 2부터 시작하여 하나씩 확인해서 isPrime[i]가 true이면 그 수는 소수이다.  

- 소수의 배수는 소수가 아니므로 소수인 수의 배수 index의 isPrime을 모두 false로 바꿔준다.  

- i의 i-1 배수까지는 이전에 이미 체크를 했으므로 i\*i부터 시작하는데, 이 문제는 최대 400만까지 input으로 들어오므로 *long*을 써야 했다.  

### 소수의 연속합 경우의 수 계산 1
```java
int l,sum,ans;
l = 0;
sum = 0;
ans = 0;
for(int r=0;r<primes.size();r++){
    sum += primes.get(r);
    while(sum > N){
        sum -= primes.get(l++);
    }
    if(sum == N) ans++;
}
```
- *투 포인터* 개념을 사용했다.  

- 오른쪽 포인터는 계속 자기 위치 index 소수 값을 sum에 더한다.  

- 왼쪽 포인터는 sum 값이 N보다 크다면 계속 오른쪽으로 이동시키면서 자신의 위치 index 소수 값을 sum에서 뺀다.  

- 이 작업을 하고 나서 sum이 N과 같다면, 소수의 연속합이 N이 되는 경우의 수 하나를 찾은 것이다.  

### 소수의 연속합 경우의 수 계산 2
```java
pSum = new int[primes.size()+1];
for(int i=0;i<primes.size();i++){
    pSum[i+1] = pSum[i] + primes.get(i);
}

int l,ans;
l = 0;
ans = 0;
for(int r=1;r<=primes.size();r++){
    while(pSum[r] - pSum[l] > N){
        l++;
    }
    if(pSum[r] - pSum[l] == N) ans++;
}
```
- *prefix Sum* 개념을 사용한다.  

- pSum[i] == 앞에서부터 i번 째(0 index == 1 번째)까지의 prefix Sum 값  

- 위와 마찬가지로 r값은 계속 옮겨주면서 l값을 pSum[r] - pSum[l]이 N보다 작거나 같을 때까지 빼주면, 연속합이 N이 되는 경우의 수를 찾을 수 있다.


## ✅ 후기
알고리즘에서 은근 자주 사용되는 두 가지 개념이 간단하게 엮여있어 좋은 문제였다.