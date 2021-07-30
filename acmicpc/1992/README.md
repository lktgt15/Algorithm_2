# 1992번 쿼드트리
[문제 보러가기](https://www.acmicpc.net/problem/1992)

## 🅰 설계
### 1. 어떤 방법을 사용할 것인가?
- 정해진 범위를 탐색하는 행동은 같고, 
  그 범위 내에서 점이 모두 같지 않으면 4가지 범위로 나눠서 각 범위마다 다시 같은 행동을 반복한다.  
- 재귀로 행동을 반복하면서 인자로 범위를 정해주는 방법을 생각할 수 있다.

### 2. 재귀
```java
static void split(int sy,int sx,int ey,int ex,int size){
        boolean allSame = true;
        char pivot = map[sy][sx];
        for(int i=sy;i<=ey && allSame;i++){
            for(int j=sx;j<=ex;j++){
                if(pivot != map[i][j]){
                    allSame = false;
                    break;
                }
            }
        }

        if(allSame){
            sb.append(pivot);
            return;
        }

        size /= 2;

        sb.append("(");
        // left top
        split(sy,sx,ey-size,ex-size,size);

        // right top
        split(sy,sx+size,ey-size,ex,size);

        // left bottom
        split(sy+size,sx,ey,ex-size,size);

        // right bottom
        split(sy+size,sx+size,ey,ex,size);

        sb.append(")");
    }
```
- `split(sy,sx,ey,ex,size)`는 sy,sx부터 ey,ex까지 범위를 탐색하면서 모두 같은 점인지 확인하는 메소드다.  
1. sy,sx 부터 ey,ex까지  
   1-1. 모두 같은 점이면 그 점의 타입을 답에 추가하고 종료시킨다.  
   1-2. 모두 같은 점이 아니면 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래로 나눠서 1을 반복한다.

## ✅ 후기
기본적인 재귀, 분할정복이라고 할 수 있는 문제였다. 재귀는 시작,종료 시점이 중요한데 그걸 알 수 있는 문제였다.