import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args)
    {
        String a = "123";
        System.out.println(a.substring(0, 2));
    }

    public static String numericOrder(String inputString){
        String[] wordsWithNumbers = inputString.split(" ");
        String[] answer = new String[wordsWithNumbers.length];
        String nums = "0123456789";
        for(int i = 0; i < wordsWithNumbers.length; i++){
            String tempWord = wordsWithNumbers[i];
            String intPlace = "";
            String wordInPlace = "";
            for(int j = 0; j < tempWord.length(); j++){
                if(nums.indexOf(tempWord.charAt(j)) == -1){
                    wordInPlace += tempWord.charAt(j);
                }
                else {
                    intPlace += tempWord.charAt(j);
                }
            }
            answer[Integer.parseInt(intPlace) - 1] = wordInPlace;
        }
        return "";
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
