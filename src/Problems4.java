import java.util.*;

public class Problems4 {
    public static void main(String[] args){
        System.out.println("----- #1");
        System.out.println(nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));

        System.out.println("----- #3");
        System.out.println(Arrays.toString(binarySystem(3)));
        System.out.println(Arrays.toString(binarySystem(4)));

        System.out.println("----- #4");
        System.out.println(alphabeticRow("abcdjuwx"));
        System.out.println(alphabeticRow("klmabzyxw"));

        System.out.println("----- #5");
        System.out.println(letterPattern("aaaddcbb"));
        System.out.println(letterPattern("vvvvaajaaaaa"));

        System.out.println("----- #6");
        System.out.println(convertToNum("eight"));
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(convertToNum("thirty one"));

        System.out.println("----- #7");

        System.out.println(uniqueSubstring("12342324"));
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898"));

    }

    private static String nonRepeatable(String inputString){
        String answer = "";
        int index = 0;
        return deleteDuplicatesRecursion(inputString, index, answer);
    }

    private static String deleteDuplicatesRecursion(String inputString, int index, String answer){
        String currentAnswer = answer;
        if(index == inputString.length() - 1) return currentAnswer;
        if(currentAnswer.indexOf(inputString.charAt(index)) == -1) currentAnswer += inputString.charAt(index);
        index++;
        currentAnswer = deleteDuplicatesRecursion(inputString, index, currentAnswer);
        return currentAnswer;
    }

    public static String[] binarySystem(int len){
        List<String> nums = new ArrayList<>();
        for(int i = 0; i < Math.pow(2, len); i++){
            String tempBinaryNum = convertToBinary(i, len);
            if(!tempBinaryNum.contains("00")) nums.add(tempBinaryNum);
        }
        return nums.toArray(new String[0]);
    }

    private static String convertToBinary(int value, int len){
        String answer = "";
        if(value == 0) answer = "0";
        while(value > 0){
            answer += String.valueOf(value % 2);
            value /= 2;
        }
        if(answer.length() < len){
            int ansLen = answer.length();
            for(int i = 0; i < len - ansLen; i++){
                answer += "0";
            }
        }
        return new StringBuilder(answer).reverse().toString();
    }

    public static String alphabeticRow(String input){
        input += " ";
        int maxCount = 0;
        String currentAnswer = "";
        String tempAnswer = "";
        for(int i = 0; i < input.length() - 1; i++){
            if(Math.abs((int) input.charAt(i) - (int) input.charAt(i + 1)) == 1){
                tempAnswer += input.charAt(i);
            }
            else{
                if(tempAnswer.length() + 1 >= maxCount){
                    tempAnswer += input.charAt(i);
                    maxCount = tempAnswer.length();
                    currentAnswer = tempAnswer;
                }
                tempAnswer = "";
            }
        }
        return currentAnswer;
    }

    public static String letterPattern(String input){
        input += " ";
        ArrayList<String> lettersCount = new ArrayList<>();
        int count = 1;
        char tempChar = ' ';
        for(int i = 0; i < input.length() - 1; i++){
            tempChar = input.charAt(i);
            if(tempChar == input.charAt(i + 1)){
                count++;
            }
            else{
                lettersCount.add(tempChar + String.valueOf(count));
                count = 1;
            }
        }
        for (int i = 0; i < lettersCount.size(); i++) {
            for (int j = 1; j < lettersCount.size() - i; j++) {
                int firstNum = Integer.parseInt(lettersCount.get(j-1).substring(1));
                int secondNum = Integer.parseInt(lettersCount.get(j).substring(1));
                if(firstNum > secondNum){
                    Collections.swap(lettersCount, j-1, j);
                }
                if(firstNum == secondNum){
                    if((int) lettersCount.get(j-1).charAt(0) > (int)lettersCount.get(j).charAt(0)){
                        Collections.swap(lettersCount, j-1, j);
                    }
                }
            }
        }
        return convertArrayToString(lettersCount);
    }

    private static String convertArrayToString(ArrayList<String> inputArray){
        String completeString = "";
        for(String elem : inputArray){
            completeString += elem;
        }
        return completeString;
    }

    public static int convertToNum(String num){
        HashMap<String, Integer> numsInWords = getNumsHashMap();
        int result = 0;
        String[] wordsInNum = num.split(" ");
        for(String oneWord : wordsInNum){
            if(oneWord.equals("hundred") | oneWord.equals("thousand")){
                result *= numsInWords.get(oneWord);
            }
            else{
                result += numsInWords.get(oneWord);
            }
        }
        return result;
    }

    private static HashMap<String, Integer> getNumsHashMap(){
        HashMap<String, Integer> stringToNum = new HashMap<>();
        stringToNum.put("one", 1);
        stringToNum.put("two", 2);
        stringToNum.put("three", 3);
        stringToNum.put("four", 4);
        stringToNum.put("five", 5);
        stringToNum.put("six", 6);
        stringToNum.put("seven", 7);
        stringToNum.put("eight", 8);
        stringToNum.put("nine", 9);
        stringToNum.put("ten", 10);
        stringToNum.put("eleven", 11);
        stringToNum.put("twelve", 12);
        stringToNum.put("thirteen", 13);
        stringToNum.put("fourteen", 14);
        stringToNum.put("fifteen", 15);
        stringToNum.put("sixteen", 16);
        stringToNum.put("seventeen", 17);
        stringToNum.put("eighteen", 18);
        stringToNum.put("nineteen", 19);
        stringToNum.put("twenty", 20);
        stringToNum.put("thirty", 30);
        stringToNum.put("forty", 40);
        stringToNum.put("fifty", 50);
        stringToNum.put("sixty", 60);
        stringToNum.put("seventy", 70);
        stringToNum.put("eighty", 80);
        stringToNum.put("ninety", 90);
        stringToNum.put("hundred", 100);
        stringToNum.put("thousand", 1000);
        return stringToNum;
    }


    public static String uniqueSubstring(String inputString){
        int i = 0;
        int previousIndex = 0;
        String answer = "";
        String tempAnswer = "";
        boolean saveIndex = true;
        while(i < inputString.length() - 1){
            if(saveIndex){
                previousIndex = i;
                saveIndex = false;
            }
            if(inputString.charAt(i) != inputString.charAt(i + 1) & tempAnswer.indexOf(inputString.charAt(i)) == -1){
                tempAnswer += inputString.charAt(i);
                i++;
            }
            else{
                if(tempAnswer.length() > answer.length()){
                    answer = tempAnswer;
                }
                tempAnswer = "";
                saveIndex = true;
                i = previousIndex + 1;
            }
        }
        return answer == "" ? answer + inputString.charAt(0) : answer;
    }

    
}
