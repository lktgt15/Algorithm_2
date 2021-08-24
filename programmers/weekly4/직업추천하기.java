import java.util.*;

class 직업추천하기 {
    public String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        Map<String,Integer> pref = new HashMap<>();
        Map<String,Integer> mp = new HashMap<>();
        for(int i=0;i<languages.length;i++){
            pref.put(languages[i],preference[i]);
        }
        int ansval = 0;

        for(String content : table){
            String[] splited = content.split(" ");
            String part = splited[0];
            int subansval = 0;
            for(int i=1;i<splited.length;i++){
                String curLang = splited[i];
                int curScore = splited.length-i;
                subansval += pref.getOrDefault(curLang,0)*curScore;
            }

            if(ansval < subansval){
                ansval = subansval;
                answer = part;
            }else if(ansval == subansval && answer.compareTo(part) > 0){
                answer = part;
            }
        }


        return answer;
    }
}