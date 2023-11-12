import java.util.*;

public class Problems4 {
    public static void main(String[] args){
        System.out.println("----- #1");
        System.out.println(nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));

        System.out.println("----- #2");
        System.out.println(Arrays.toString(generateBrackets(1)));
        System.out.println(Arrays.toString(generateBrackets(2)));
        System.out.println(Arrays.toString(generateBrackets(3)));

        System.out.println("----- #3");
        System.out.println(Arrays.toString(binarySystem(3)));
        System.out.println(Arrays.toString(binarySystem(4)));

        System.out.println("----- #4");
        System.out.println(alphabeticRow("aba"));
        System.out.println(alphabeticRow("klmabzyxw"));

        System.out.println("----- #5");
        System.out.println(letterPattern("aaaddcbb"));
        System.out.println(letterPattern("vvvvaajaaaaa"));

        System.out.println("----- #6");
        System.out.println(convertToNum("zero"));
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(convertToNum("thirty one"));

        System.out.println("----- #7");
        System.out.println(uniqueSubstring("12342324"));
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898"));

        System.out.println("----- #8");
        System.out.println(shortestWay(new int[][] {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(shortestWay(new int[][] {{2, 7, 3}, {1, 4, 8}, {4, 5, 9}}));

        System.out.println("----- #9");
        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));

        System.out.println("----- #10");
        System.out.println(switchNums(519, 723));
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259));
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

    private static String[] generateBrackets(int count){

        ArrayList<String> answer = new ArrayList<>();
        recursionGeneration(count, answer,new String());
        return answer.toArray(new String[0]);
    }

    private static void recursionGeneration(int count, ArrayList<String> curAnswers, String answer){
        if(answer.length() / 2 == count){
            curAnswers.add(answer);
        }
        else{
            int leftBrackets = 0;
            int rightBrackets = 0;
            for(int i = 0; i < answer.length(); i++){
                if(answer.charAt(i) == '('){
                    leftBrackets++;
                }
                else{
                    rightBrackets++;
                }
            }
            if(leftBrackets == rightBrackets){
                recursionGeneration(count, curAnswers, answer + "(");
            }
            else{
                if(rightBrackets < leftBrackets){
                    if(leftBrackets < count){
                        recursionGeneration(count, curAnswers, answer + "(");
                    }
                    recursionGeneration(count, curAnswers, answer + ")");
                }
            }
        }
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
        input = " " + input + " ";
        int maxCount = 0;
        String currentAnswer = "";
        String tempAnswer = "";
        for(int i = 1; i < input.length() - 1; i++){
            if((int) input.charAt(i) - (int) input.charAt(i + 1) == 1){
                if((int) input.charAt(i-1) - (int) input.charAt(i) == -1){
                    if(tempAnswer.length() > maxCount){
                        maxCount = tempAnswer.length();
                        currentAnswer = tempAnswer;
                    }
                    tempAnswer = "";
                }
                tempAnswer += input.charAt(i);
            }
            else if((int) input.charAt(i) - (int) input.charAt(i + 1) == -1){
                if((int) input.charAt(i-1) - (int) input.charAt(i) == 1){
                    if(tempAnswer.length() > maxCount){
                        maxCount = tempAnswer.length();
                        currentAnswer = tempAnswer;
                    }
                    tempAnswer = "";
                }
                tempAnswer += input.charAt(i);
            }
            else{
                tempAnswer += input.charAt(i);
                if(tempAnswer.length() > maxCount){
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
        return convertArrayToString(lettersCount.toArray(new String[0]));
    }

    private static String convertArrayToString(String[] inputArray){
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
        stringToNum.put("zero", 0);
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
        return answer.isEmpty() ? answer + inputString.charAt(0) : answer;
    }

    public static int shortestWay(int[][] matrix){
        int matrixLen = matrix.length;
        int maxIterations = matrix.length * matrix[0].length;
        int endPointWay = matrix[matrixLen - 1][matrixLen - 1];
        int curRow = matrixLen - 1;
        int curColumn = matrixLen - 1;
        for(int i = 0; i < maxIterations; i++){
            if(curRow == 0 & curColumn == 0){
                break;
            }
            if(curRow - 1 >= 0 & curColumn - 1 >= 0){
                if(matrix[curRow-1][curColumn] < matrix[curRow][curColumn-1]){
                    curRow -= 1;
                    endPointWay += matrix[curRow][curColumn];
                }
                else{
                    curColumn -= 1;
                    endPointWay += matrix[curRow][curColumn];
                }
            }
            else if(curRow - 1 < 0){
                while(curColumn > 0){
                    curColumn -= 1;
                    endPointWay += matrix[curRow][curColumn];
                }
                break;
            }
            else if(curColumn - 1 < 0){
                while(curRow > 0){
                    curRow -= 1;
                    endPointWay += matrix[curRow][curColumn];
                }
                break;
            }
        }
        return endPointWay;
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
            answer[Integer.parseInt(intPlace) - 1] = wordInPlace + " ";
        }
        return convertArrayToString(answer);
    }

    public static int switchNums(int a, int b){
        String secondNum = String.valueOf(b);
        ArrayList<Integer> numsInFirst = new ArrayList<>();
        while (a > 0){
            numsInFirst.add(a % 10);
            a /= 10;
        }
        Collections.sort(numsInFirst, Collections.reverseOrder());
        for(int i = 0; i < numsInFirst.size(); i++){
            for(int j = 0; j < secondNum.length(); j++){
                if(numsInFirst.get(i) > Integer.valueOf(String.valueOf(secondNum.charAt(j)))){
                    secondNum = secondNum.substring(0, j) + numsInFirst.get(i).toString() + secondNum.substring(j + 1);
                    break;
                }
            }
        }
        return Integer.parseInt(secondNum);
    }
}
