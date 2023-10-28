import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            i -= 50;
        }
    }

    private static String convertToBinary(int i){
        String answer = "";
        while(i > 0){
            answer += String.valueOf(i % 2);
            i /= 2;
        }
        return new StringBuilder(answer).reverse().toString();
    }

    private static void permutations(String[] words, boolean[] visited, List<String> currentWords, List<String[]> splits) {
        if(words.length == currentWords.size()){
            splits.add(currentWords.toArray(new String[0]));
            System.out.println(currentWords);
            return;
        }
        for(int i = 0; i < words.length; i++){
            if(!visited[i]){
                visited[i] = true;
                currentWords.add(words[i]);
                permutations(words, visited, currentWords, splits);
                currentWords.remove(currentWords.size()-1);
                visited[i] = false;
            }
        }

    }
}
