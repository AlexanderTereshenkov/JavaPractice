import java.util.Arrays;

public class Problems5 {
    public static void main(String[] args) {
        System.out.println("----- #1");
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));

        System.out.println("----- #3");
        System.out.println(digitsCount(4666));
        System.out.println(digitsCount(544));
        System.out.println(digitsCount(121317));
        System.out.println(digitsCount(0));
        System.out.println(digitsCount(12345));
        System.out.println(digitsCount(1289396387328L));

        System.out.println("----- #5");
        System.out.println(takeDownAverage(new String[] {"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(takeDownAverage(new String[] {"10%"}));
        System.out.println(takeDownAverage(new String[] {"53%", "79%"}));

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
}
