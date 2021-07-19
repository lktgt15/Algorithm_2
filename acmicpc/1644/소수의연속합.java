import java.io.*;
import java.util.*;

public class 소수의연속합 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static boolean[] isPrime;
    static List<Integer> primes;
    static int[] pSum;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        primes = new ArrayList<>();
        isPrime = new boolean[N+1];

        init();
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
        System.out.println(ans);
    }

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
}
