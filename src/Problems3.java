import java.util.*;

public class Problems3 {
    public static void main(String[] args){
        System.out.println("----- #1");
        System.out.println(replaceVovels("Apple"));
        System.out.println(replaceVovels("Even if you did this " +
                "task not by yourself, you have to understand every single line of code."));

        System.out.println("----- #2");
        System.out.println(stringTransform("hello"));
        System.out.println(stringTransform("bookkeeper"));

        System.out.println("----- #3");
        System.out.println(doesBlockFit(1, 3, 5, 4, 5));
        System.out.println(doesBlockFit(1, 8, 1, 1, 1));
        System.out.println(doesBlockFit(1, 2, 2, 1, 1));

        System.out.println("----- #4");
        System.out.println(numCheck(243));
        System.out.println(numCheck(52));

        System.out.println("----- #5");
        System.out.println(countRoots(new int[]{1, -3, 2}));
        System.out.println(countRoots(new int[]{2, 5, 2}));
        System.out.println(countRoots(new int[]{1, -6, 9}));

        System.out.println("----- #6");
        System.out.println(Arrays.toString(salesData(new String[][]{{"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Banana", "Shop2", "Shop3", "Shop4"},
                {"Orange", "Shop1", "Shop3", "Shop4"},
                {"Pear", "Shop2", "Shop4"}})));
        System.out.println(Arrays.toString(salesData(new String[][]{{"Fridge", "Shop2", "Shop3"},
                {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Laptop", "Shop3", "Shop4"},
                {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}})));

        System.out.println("----- #7");
        System.out.println(validSplit("apple egg eagle goat"));
        System.out.println(validSplit("cat fish dog goose"));

        System.out.println("----- #8");
        System.out.println(waveForm(new int[] {3, 1, 4, 2, 7, 5}));
        System.out.println(waveForm(new int[] {1, 2, 3, 4, 5}));
        System.out.println(waveForm(new int[] {1, 2, -6, 10, 3}));

        System.out.println("----- #9");
        System.out.println(commonVovel("Hello world"));
        System.out.println(commonVovel("Actions speak louder than words."));


        System.out.println("----- #10");
        System.out.println(Arrays.deepToString(dataScience(new int[][]{{1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {5, 5, 5, 5, 5},
                {7, 4, 3, 14, 2},
                {1, 0, 11, 10, 1}})));
        System.out.println(Arrays.deepToString(dataScience(new int[][]{{6, 4, 19, 0, 0},
                {81, 25, 3, 1, 17},
                {48, 12, 60, 32, 14},
                {91, 47, 16, 65, 217},
                {5, 73, 0, 4, 21}})));
    }

    public static String replaceVovels(String input){
        String result = input;
        String vovels = "aeiouy";
        for(int i = 0; i < vovels.length(); i++){
            if(input.toLowerCase().indexOf(vovels.charAt(i)) != -1){
                result = result.replace(vovels.charAt(i), '*');
                result = result.replace(vovels.toUpperCase().charAt(i), '*');
            }
        }
        return result;
    }

    public static String stringTransform(String input){
        String result = "";
        int i = 0;
        while(i < input.length()){
            if(i != input.length() - 1 && input.charAt(i) == input.charAt(i + 1)){
                result += "Double" + input.toUpperCase().charAt(i);
                i += 2;
            }
            else{
                result += input.charAt(i);
                i++;
            }
        }
        return result;
    }

    public static boolean doesBlockFit(int a, int b, int c, int w, int h){
        int[] sides = new int[]{a, b, c};
        Arrays.sort(sides);
        return sides[0] <= Math.min(w, h) & sides[1] <= Math.max(w, h);
    }

    public static boolean numCheck(int num){
        int tempNum = num;
        int res = 0;
        while (tempNum > 0){
            res += (tempNum % 10) * (tempNum % 10);
            tempNum /= 10;
        }
        return res % 2 == num % 2;
    }

    public static int countRoots(int[] coefs){
        int d = coefs[1] * coefs[1] - 4 * coefs[0] * coefs[2];
        if(d < 0) return 0;
        if(d == 0){
            float x = -coefs[1] / 2f * coefs[0];
            return x % 1 == 0 ? 1 : 0;
        }
        double x1 = (-coefs[1] + Math.sqrt(d)) / (2f * coefs[0]);
        double x2 = (-coefs[1] - Math.sqrt(d)) / (2f * coefs[0]);
        return (x1 % 1 == 0 ? 1 : 0) + (x2 % 1 == 0 ? 1 : 0);
    }

    public static String[] salesData(String[][] goods){
        int maxShopCount = 0;
        List<String> maxGoods = new ArrayList<>();
        for(int i = 0; i < goods.length; i++){
            if(goods[i].length - 1 > maxShopCount) maxShopCount = goods[i].length - 1;
        }
        for(int i = 0; i < goods.length; i++){
            if(goods[i].length - 1 == maxShopCount) maxGoods.add(goods[i][0]);
        }
        return maxGoods.toArray(new String[0]);
    }

    public static boolean validSplit(String input){
        String[] str = input.split(" ");
        List<String> cur = new ArrayList<>();
        boolean[] visited = new boolean[str.length];
        List<String[]> allSplits = new ArrayList<>();
        permutations(str, visited, cur, allSplits);
        return !allSplits.isEmpty();
    }

    private static void permutations(String[] words, boolean[] visited, List<String> currentWords, List<String[]> splits) {
        if(words.length == currentWords.size()){
            boolean isValid = true;
            for(int i = 0; i < currentWords.size() - 1; i++){
                if(currentWords.get(i).charAt(currentWords.get(i).length() - 1) != currentWords.get(i + 1).charAt(0))
                    isValid = false;
            }
            if(isValid) splits.add(currentWords.toArray(new String[0]));
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

    public static boolean waveForm(int[] nums){
        ArrayList<Boolean> bools = new ArrayList<>();
        for(int i = 0; i < nums.length - 1; i++){
            bools.add(nums[i] < nums[i + 1]);
        }
        for(int i = 0; i < bools.size() - 1; i++){
            if(bools.get(i) == bools.get(i + 1)) return false;
        }
        return true;
    }

    public static char commonVovel(String s){
        String vowels = "aeiouy";
        int maxIndex = 0;
        int[] maxCount = new int[]{0, 0, 0, 0, 0, 0};
        for (int i = 0; i < s.length(); i++){
            int charIndex = vowels.indexOf(s.toLowerCase().charAt(i));
            if(charIndex != -1) maxCount[charIndex]++;
        }
        int maxValue = 0;
        for(int i = 0; i < maxCount.length; i++){
            if(maxCount[i] > maxValue){
                maxValue = maxCount[i];
                maxIndex = i;
            }
        }
        return vowels.charAt(maxIndex);
    }

    public static int[][] dataScience(int[][] data){
        for(int i = 0; i < data.length; i++){
            float mid = 0;
            for(int j = 0; j < data.length; j++){
                if(j != i){
                    mid += data[j][i];
                }
            }
            mid /= (data.length - 1);
            data[i][i] = Math.round(mid);
        }
        return data;
    }
}
