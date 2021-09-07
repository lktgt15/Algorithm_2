import java.util.*;
import java.io.*;

class 복서정렬하기 {
    public int[] solution(int[] weights, String[] head2head) {
        int[] answer = new int[weights.length];
        PriorityQueue<Boxer> pq = new PriorityQueue<>();
        for(int i=0;i<weights.length;i++){
            // i번째 복서
            long total = 0;
            long wins = 0;
            int winOverMyWeight = 0;
            for(int j=0;j<head2head.length;j++){
                char c = head2head[i].charAt(j);
                switch(c){
                    case 'N':
                        break;
                    case 'W':
                        wins++;
                        if(weights[i] < weights[j]) winOverMyWeight++;
                        total++;
                        break;
                    case 'L':
                        total++;
                        break;
                }
            }
            if(total == 0) {
                pq.add(new Boxer(i,weights[i],0,0L));
            }else{
                long winrate = (long)((wins*10000000000L)/total);
                pq.add(new Boxer(i,weights[i],winOverMyWeight,winrate));
            }
        }
        for(int i=0;i<weights.length;i++){
            answer[i] = pq.poll().num+1;
        }

        return answer;
    }

    static class Boxer implements Comparable<Boxer>{
        long winrate;
        int num,weight,winOverMyWeight;
        public Boxer(){}
        public Boxer(int num,int weight,int winOverMyWeight,long winrate){
            this.num = num;
            this.weight = weight;
            this.winOverMyWeight = winOverMyWeight;
            this.winrate = winrate;
        }

        @Override
        public int compareTo(Boxer other){
            // 1.
            if(this.winrate != other.winrate) return Long.compare(other.winrate,this.winrate);
            // 2.
            if(this.winOverMyWeight != other.winOverMyWeight) return Integer.compare(other.winOverMyWeight,this.winOverMyWeight);
            // 3.
            if(this.weight != other.weight) return Integer.compare(other.weight,this.weight);
            // 4.
            return Integer.compare(this.num,other.num);
        }
    }
}