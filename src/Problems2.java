import java.util.*;

public class Problems2 {
    public static void main(String[] args){
        System.out.println("----- #1");
        System.out.println(duplicateChars("Donald"));
        System.out.println(duplicateChars("orange"));

        System.out.println("----- #2");
        System.out.println(getInitials("Ryan Gosling"));
        System.out.println(getInitials("Barack Obama"));

        System.out.println("----- #3");
        System.out.println(differenceEvenOdd(new int[]{44, 32, 86, 19}));
        System.out.println(differenceEvenOdd(new int[]{22, 50, 16, 63, 31, 55}));

        System.out.println("----- #4");
        System.out.println(equalToAvg(new int[]{1, 2, 3, 4, 5}));
        System.out.println(equalToAvg(new int[]{1, 2, 3, 4, 6}));

        System.out.println("----- #5");
        indexMult(new int[]{1, 2, 3});
        System.out.println();
        indexMult(new int[]{3, 3, -2, 408, 3, 31});
        System.out.println();

        System.out.println("----- #6");
        System.out.println(reverse("Hello World"));
        System.out.println(reverse("The quick brown fox."));

        System.out.println("----- #7");
        System.out.println(Tribonacci(7));
        System.out.println(Tribonacci(11));

        System.out.println("----- #8");
        System.out.println(pseudoHash(5));
        System.out.println(pseudoHash(10));
        System.out.println(pseudoHash(0));
 

        System.out.println("----- #9");
        System.out.println(botHelper("Hello, I’m under the water, please help"));
        System.out.println(botHelper("Two pepperoni pizzas please"));

        System.out.println("----- #10");
        System.out.println(isAnagram("listen", "silent"));
        System.out.println(isAnagram("eleven plus two", "twelve plus one"));
        System.out.println(isAnagram("hello", "world"));
    }

    public static boolean duplicateChars(String input){
        Set<String> letters = new HashSet<>();
        for(char temp : input.toLowerCase().toCharArray()){
            letters.add(String.valueOf(temp));
        }
        return !(input.length() == letters.size());
    }

    public static String getInitials(String name){
        String result = "";
        String[] splitString = name.split(" ");
        for(String element : splitString){
            result += element.charAt(0);
        }
        return result;
    }

    public static int differenceEvenOdd(int[] numsArray){
        int odd = 0;
        int even = 0;
        for(int elem : numsArray){
            if(elem % 2 == 0){
                even += elem;
            }
            else{
                odd += elem;
            }
        }
        return Math.abs(even - odd);
    }

    public static boolean equalToAvg(int[] nums){
        float mid = (float)Arrays.stream(nums).sum() / nums.length;
        for(int elem : nums){
            if(elem == mid) return true;
        }
        return false;
    }

    public static int[] indexMult(int[] numsArray){
        for(int i = 0; i < numsArray.length; i++){
            numsArray[i] = numsArray[i] * i;
        }
        for(int elem : numsArray){
            System.out.print(elem + ", ");
        }
        return numsArray;
    }

    public static String reverse(String word){
        String result = "";
        for(int i = word.length() - 1; i >= 0; i--){
            result += word.charAt(i);
        }
        return result;
    }

    public static int Tribonacci(int pos){
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(0); nums.add(0); nums.add(1);
        for(int i = 3; i < pos; i++){
            nums.add(nums.get(i-1) + nums.get(i-2) + nums.get(i-3));
        }
        return nums.get(nums.size()-1);
    }

    private static String pseudoHash(int len){
        String result = "";
        String alphabet = "abcdef0123456789";
        for(int i = 0; i < len; i++){
            result += alphabet.charAt((int)(Math.random() * alphabet.length()));
        }
        return result;
    }

    public static String botHelper(String command){
        String[] commands = command.split(" ");
        for (String elem : commands){
            if(elem.equalsIgnoreCase("help")){
                return "Calling for a staff member";
            }
        }
        return "Keep waiting";
    }

    public static boolean isAnagram(String word1, String word2){
        char[] firstWordLetters = word1.toCharArray();
        char[] secondWordLetters = word2.toCharArray();
        if(firstWordLetters.length != secondWordLetters.length) return false;
        Arrays.sort(firstWordLetters);
        Arrays.sort(secondWordLetters);
        return Arrays.equals(firstWordLetters, secondWordLetters);
    }

}
