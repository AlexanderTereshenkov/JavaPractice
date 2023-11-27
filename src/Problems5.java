import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problems5 {
    public static void main(String[] args) {
        System.out.println("----- #1");
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));

        System.out.println("----- #2");
        System.out.println(spiderVsFly("H3", "E2"));
        System.out.println(spiderVsFly("A4", "B2"));
        System.out.println(spiderVsFly("A4", "C2"));

        System.out.println("----- #3");
        System.out.println(digitsCount(4666));
        System.out.println(digitsCount(544));
        System.out.println(digitsCount(121317));
        System.out.println(digitsCount(0));
        System.out.println(digitsCount(12345));
        System.out.println(digitsCount(1289396387328L));

        System.out.println("----- #4");
        System.out.println(totalPoints(new String[] {"cat", "create", "sat"}, "caster"));
        System.out.println(totalPoints(new String[] {"trance", "recant"}, "recant"));
        System.out.println(totalPoints(new String[] {"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));

        System.out.println("----- #5");
        System.out.println(Arrays.deepToString(sumsUp(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.deepToString(sumsUp(new int[]{1, 2, 3, 7, 9})));
        System.out.println(Arrays.deepToString(sumsUp(new int[]{10, 9, 7, 2, 8})));
        System.out.println(Arrays.deepToString(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7})));

        System.out.println("----- #6");
        System.out.println(takeDownAverage(new String[] {"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(takeDownAverage(new String[] {"10%"}));
        System.out.println(takeDownAverage(new String[] {"53%", "79%"}));

        System.out.println("----- #7");
        System.out.println(caesarCipher("encode","hello world", 3));
        System.out.println(caesarCipher("encode", "almost last task!", 4));
        System.out.println(caesarCipher("decode", "KHOOR ZRUOG", 3));

        System.out.println("----- #8");
        System.out.println(setSetup(5, 3));
        System.out.println(setSetup(7, 3));

        System.out.println("----- #9");
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println(timeDifference("Rome", "May 1, 2000 05:40", "London"));

        System.out.println("----- #10");
        System.out.println(isNew(3));
        System.out.println(isNew(30));
        System.out.println(isNew(321));
        System.out.println(isNew(123));
    }

    private static boolean sameLetterPattern(String a, String b){
        int[] firstPattern = new int[a.length() - 1];
        int[] secondPattern = new int[b.length() - 1];
        if(firstPattern.length != secondPattern.length) return false;
        for(int i = 0; i < a.length() - 1; i++){
            firstPattern[i] = (int)a.charAt(i) - (int)(a.charAt(i + 1));
            secondPattern[i] = (int)b.charAt(i) - (int)(b.charAt(i + 1));
        }
        return Arrays.equals(firstPattern, secondPattern);
    }

    public static String spiderVsFly(String x1, String x2){
        StringBuilder answer = new StringBuilder();
        String map = "ABCDEFGH";
        int curIndex = map.indexOf(x1.charAt(0));
        int goal = map.indexOf(x2.charAt(0));
        int distance = Math.abs(goal - curIndex) > 4 ? ((8 - Math.abs(goal - curIndex)) * (goal < curIndex ? 1 : -1))
                : goal - curIndex;
        int direction = distance < 0 ? -1 : 1;
        int startLevel = Integer.parseInt(x1.replaceAll("[^0-9]", ""));
        int finishLevel = Integer.parseInt(x2.replaceAll("[^0-9]", ""));
        if(Math.abs(distance) <= 2){
            if(startLevel >= finishLevel){
                firstCase(answer, startLevel, finishLevel, map, curIndex, goal, direction, -1);
            }
            else{
                firstCase(answer, startLevel, finishLevel, map, curIndex, goal, direction, 1);
            }
        }
        else{
            secondCase(answer, startLevel, finishLevel, map, curIndex, goal);
        }
        return answer.deleteCharAt(answer.length()-1).toString();
    }

    private static void firstCase(StringBuilder answer, int startLevel, int finishLevel, String map,
                                  int curIndex, int goal, int direction, int coef){
        while(startLevel != finishLevel){
            answer.append(map.charAt(curIndex) + String.valueOf(startLevel) + "-");
            startLevel += coef;
        }
        answer.append(map.charAt(curIndex) + String.valueOf(startLevel) + "-");
        while (curIndex != goal){
            curIndex = (curIndex + direction) % map.length();
            answer.append(map.charAt(curIndex) + String.valueOf(startLevel) + "-");
        }
    }

    private static void secondCase(StringBuilder answer, int startLevel, int finishLevel, String map, int curIndex, int goal){
        while(startLevel != 0){
            answer.append(map.charAt(curIndex) + String.valueOf(startLevel) + "-");
            startLevel--;
        }
        answer.append("A0" + "-");
        startLevel++;
        while(startLevel != finishLevel){
            answer.append(map.charAt(goal) + String.valueOf(startLevel) + "-");
            startLevel++;
        }
        answer.append(map.charAt(goal) + String.valueOf(startLevel) + "-");
    }

    private static int digitsCount(long input){
        int count = 0;
        if(input == 0) return 1;
        return recursionDigitsCount(input, count);
    }

    private static int recursionDigitsCount(long inputNum, int counter){
        if(inputNum == 0) return counter;
        counter++;
        inputNum /= 10;
        return recursionDigitsCount(inputNum, counter);
    }

    public static int totalPoints(String[] words, String guessedWord){
        int points = 0;
        HashMap<Character, Integer> walidLetters = new HashMap<>();
        for(int i = 0; i < guessedWord.length(); i++){
            if(walidLetters.get(guessedWord.charAt(i)) == null){
                walidLetters.put(guessedWord.charAt(i), 1);
            }
            else{
                walidLetters.put(guessedWord.charAt(i), walidLetters.get(guessedWord.charAt(i)) + 1);
            }
        }
        for(String word : words){
            HashMap<Character, Integer> letters = getWordsLetters(word);
            boolean isValid = true;
            for(Character key : letters.keySet()){
                if(!(walidLetters.get(key) != null && letters.get(key) <= walidLetters.get(key))){
                    isValid = false;
                }
            }
            if(isValid){
                points += word.length() - 2 + (word.length() == 6 ? 50 : 0);
            }
        }
        return points;
    }

    private static HashMap<Character, Integer> getWordsLetters(String word){
        HashMap<Character, Integer> letters = new HashMap<>();
        for(int i = 0; i < word.length(); i++){
            if(letters.get(word.charAt(i)) == null){
                letters.put(word.charAt(i), 1);
            }
            else{
                letters.put(word.charAt(i), letters.get(word.charAt(i)) + 1);
            }
        }
        return letters;
    }

    public static int[][] sumsUp(int[] nums){
        ArrayList<int[]> answer = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == 8){
                    int[] temp = new int[]{nums[i], nums[j]};
                    Arrays.sort(temp);
                    answer.add(temp);
                }
            }
        }
        for(int i = 0; i < answer.size(); i++){
            for(int j = 1; j < answer.size() - i; j++){
                int firstDiff = Math.abs(getIndex(nums, answer.get(j-1)[0]) - getIndex(nums, answer.get(j-1)[1]));
                int secondDiff = Math.abs(getIndex(nums, answer.get(j)[0]) - getIndex(nums, answer.get(j)[1]));
                if(firstDiff > secondDiff){
                    int[] temp = answer.get(j);
                    answer.set(j, answer.get(j-1));
                    answer.set(j-1, temp);
                }
            }
        }
        return answer.toArray(new int[0][0]);
    }

    private static int getIndex(int[] nums, int element){
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == element){
                index = i;
            }
        }
        return index;
    }

    public static String takeDownAverage(String[] grades){
        float p = 0;
        for(String elem : grades){
            String temp = "";
            for(int i = 0; i < elem.length(); i++){
                if(elem.charAt(i) != '%') temp += elem.charAt(i);
            }
            p += Float.parseFloat(temp);
        }
        float percent = (p / (float)grades.length) - 5;

        return (int) Math.floor(percent * (grades.length + 1) - p) + "%";
    }

    private static String caesarCipher(String command, String message, int shift){
        message = message.toUpperCase();
        String answer = "";
        if(command.equals("encode")){
            answer = alphabetManipulation(message, 1, shift);
        } else if (command.equals("decode")) {
            answer = alphabetManipulation(message, -1, shift);
        }
        return answer;
    }

    private static String alphabetManipulation(String message, int command, int shift){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String answer = "";
        for(int i = 0; i < message.length(); i++){
            int index = alphabet.indexOf(message.charAt(i));
            if(index != -1){
                answer += alphabet.charAt((index + (shift * command)) % alphabet.length());
            }
            else{
                answer += message.charAt(i);
            }
        }
        return command == -1 ? answer.toLowerCase() : answer;
    }

    private static int setSetup(int n, int k){
        return factorial(n, 1) / (factorial(n-k, 1));
    }

    private static int factorial(int n, int tempAnswer){
        if(n == 0) return tempAnswer;
        tempAnswer *= n;
        n--;
        return factorial(n, tempAnswer);
    }

    public static String timeDifference(String cityA, String time, String cityB){
        HashMap<String, int[]> months = getMonths();
        HashMap<String, int[]> times = getGMT();
        int newDay = 0;
        String newMonth = "";
        int newYear = 0;
        String[] monthsCalendar = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int[] gmt1 = times.get(cityA);
        int[] gmt2 = times.get(cityB);
        int[] diff = new int[2];
        diff[0] = -gmt1[0] + gmt2[0];
        diff[1] = -gmt1[1] + gmt2[1];
        String[] data = getData(time);
        String[] tempClock = data[3].split(":");
        int[] clock = {Integer.parseInt(tempClock[0]), Integer.parseInt(tempClock[1])};
        clock[0] = clock[0] + diff[0];
        clock[1] = clock[1] + diff[1];
        if(clock[1] > 60){
            clock[0] += 1;
            clock[1] %= 60;
        }
        if(clock[1] < 0){
            clock[0] -= 1;
            clock[1] += 60;
        }
        if(clock[0] >= 24){
            int day = Integer.parseInt(data[1]);
            day += 1;
            int[] maxDaysInMonth = months.get(data[0]);
            if(isLeapYear(Integer.parseInt(data[2])) & monthsCalendar[maxDaysInMonth[1]].equals("February")) maxDaysInMonth[0]++;
            clock[0] %= 24;
            if(day > maxDaysInMonth[0]){
                newDay = day % maxDaysInMonth[0];
                newMonth = monthsCalendar[(maxDaysInMonth[1] + 1) % 12];
                newYear = newMonth.equals("January") ? Integer.parseInt(data[2]) + 1 : Integer.parseInt(data[2]);
            }
            else{
                newDay = day;
                newMonth = monthsCalendar[maxDaysInMonth[1]];
                newYear = Integer.parseInt(data[2]);

            }
        }
        else if(clock[0] < 0){
            int day = Integer.parseInt(data[1]);
            day -= 1;
            int[] maxDaysInMonth = months.get(data[0]);
            clock[0] += 24;
            if(day <= 0){
                int monthIndex = maxDaysInMonth[1] - 1 < 0 ? 12 - Math.abs(maxDaysInMonth[1] - 1): maxDaysInMonth[1] - 1;
                newMonth = monthsCalendar[monthIndex];
                maxDaysInMonth = months.get(newMonth);
                if(isLeapYear(Integer.parseInt(data[2])) & monthsCalendar[maxDaysInMonth[1]].equals("February")) maxDaysInMonth[0]++;
                newDay = day + maxDaysInMonth[0];
                newYear = newMonth.equals("December") ? Integer.parseInt(data[2]) - 1 : Integer.parseInt(data[2]);
            }
            else{
                newDay = day;
                newMonth = monthsCalendar[maxDaysInMonth[1]];
                newYear = Integer.parseInt(data[2]);
            }
        }
        else{
            int day = Integer.parseInt(data[1]);
            int[] maxDaysInMonth = months.get(data[0]);
            newDay = day;
            newMonth = monthsCalendar[maxDaysInMonth[1]];
            newYear = Integer.parseInt(data[2]);
        }
        String resultTime = (clock[0] < 10 ? "0" + clock[0]: clock[0]) + ":" + (clock[1] < 10 ? "0" + clock[1]: clock[1]);
        return newYear+"-"+(months.get(newMonth)[1]+1)+"-"+newDay+" "+resultTime;
    }

    private static boolean isLeapYear(int year){
        if(year % 100 == 0) return (year / 400f) % 1 == 0;
        return  (year / 4f) % 1 == 0;
    }

    public static String[] getData(String input){
        String[] data = new String[4];
        Pattern monthP = Pattern.compile("\\b[A-Za-z]+\\b");
        Matcher monthM = monthP.matcher(input);
        Pattern dayP = Pattern.compile("\\b\\d+(?=,)\\b");
        Matcher dayM = dayP.matcher(input);
        Pattern yearP = Pattern.compile("\\b\\d{4}\\b");
        Matcher yearM = yearP.matcher(input);
        Pattern timeP = Pattern.compile("\\b\\d{2}:\\d{2}\\b");
        Matcher timeM = timeP.matcher(input);
        while (monthM.find()) data[0] = monthM.group();
        while (dayM.find()) data[1] = dayM.group();
        while (yearM.find()) data[2] = yearM.group();
        while (timeM.find()) data[3] = timeM.group();
        return data;
    }

    public static HashMap<String, int[]> getGMT(){
        HashMap<String, int[]> times = new HashMap<>();
        times.put("Los Angeles", new int[]{-8, 0});
        times.put("New York", new int[]{-5, 0});
        times.put("Caracas", new int[]{-4, -30});
        times.put("Buenos Aires", new int[]{-3, 0});
        times.put("London", new int[]{0, 0});
        times.put("Rome", new int[]{1, 0});
        times.put("Moscow", new int[]{3, 0});
        times.put("Tehran", new int[]{3, 30});
        times.put("New Delhi", new int[]{5, 30});
        times.put("Beijing", new int[]{8, 0});
        times.put("Canberra", new int[]{10, 0});
        return times;
    }

    private static HashMap<String, int[]> getMonths(){
        HashMap<String, int[]> months = new HashMap<>();
        months.put("January", new int[]{31, 0});
        months.put("February", new int[]{28, 1});
        months.put("March", new int[]{31, 2});
        months.put("April", new int[]{30, 3});
        months.put("May", new int[]{31, 4});
        months.put("June", new int[]{30, 5});
        months.put("July", new int[]{31, 6});
        months.put("August", new int[]{31, 7});
        months.put("September", new int[]{30, 8});
        months.put("October", new int[]{31, 9});
        months.put("November", new int[]{30, 10});
        months.put("December", new int[]{31, 11});
        return months;
    }

    private static boolean isNew(int n){
        ArrayList<Integer> nums = getNumsArray(n);
        boolean isNumNew = true;
        for(int i = 1; i < nums.size() - 1; i++){
            if(nums.get(i) == 0){
                if(!(nums.get(i+1) >= nums.get(i-1))){
                    isNumNew = false;
                    break;
                }
            }
            else{
                if(!(nums.get(i) >= nums.get(i-1) & nums.get(i) <= nums.get(i+1))){
                    isNumNew = false;
                    break;
                }
            }
        }
        return isNumNew;
    }

    private static ArrayList<Integer> getNumsArray(int n){
        ArrayList<Integer> nums = new ArrayList<>();
        while (n > 0) {
            nums.add(n % 10);
            n /= 10;
        }
        Collections.reverse(nums);
        return nums;
    }
}
