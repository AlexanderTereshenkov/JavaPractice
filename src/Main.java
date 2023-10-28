
public class Main
{
    public static void main(String[] args) {
        System.out.println("----------- #1");
        System.out.println(convert(5));
        System.out.println(convert(3));
        System.out.println(convert(8));

        System.out.println("----------- #2");
        System.out.println(fitCalc(15, 1));
        System.out.println(fitCalc(24, 2));
        System.out.println(fitCalc(41, 3));

        System.out.println("----------- #3");
        System.out.println(containers(3, 4, 2));
        System.out.println(containers(5, 0, 2));
        System.out.println(containers(4, 1, 4));

        System.out.println("----------- #4");
        System.out.println(triangleType(5, 5, 5));
        System.out.println(triangleType(5, 4, 5));
        System.out.println(triangleType(3, 4, 5));
        System.out.println(triangleType(5, 1, 1));

        System.out.println("----------- #5");
        System.out.println(ternaryEvaluation(8, 4));
        System.out.println(ternaryEvaluation(1, 11));
        System.out.println(ternaryEvaluation(5, 9));

        System.out.println("----------- #6");
        System.out.println(howManyItems(22, 1.4f, 2));
        System.out.println(howManyItems(45, 1.8f, 1.9f));
        System.out.println(howManyItems(100, 2, 2));

        System.out.println("----------- #7");
        System.out.println(factorial(3));
        System.out.println(factorial(5));
        System.out.println(factorial(7));

        System.out.println("----------- #8");
        System.out.println(gcd(48, 18));
        System.out.println(gcd(52, 8));
        System.out.println(gcd(259, 28));

        System.out.println("----------- #9");
        System.out.println(ticketSeller(70, 1500));
        System.out.println(ticketSeller(24, 950));
        System.out.println(ticketSeller(53, 1250));

        System.out.println("----------- #10");
        System.out.println(tables(5, 2));
        System.out.println(tables(31, 20));
        System.out.println(tables(123, 58));
    }

    private static float convert(int l){
        return l * 3.785f;
    }

    private static int fitCalc(int minutes, int intensivity){
        return minutes * intensivity;
    }

    private static int containers(int box, int bag, int barrel){
        return (box * 20) + (bag * 50) + (barrel * 100);
    }

    private static String triangleType(int x, int y, int z){
        if(x > y + z || y > x + z || z > x + y){
            return "not a triangle";
        }
        if(x == y && y == z){
            return "isosceles";
        }
        if(x == y || x == z || y == z){
            return "equilateral";
        }
        return "different-sided";
    }

    private static int ternaryEvaluation(int a, int b){
        return a > b ? a : b;
    }

    private static int howManyItems(int n, float w, float h){
        return (int)(n / (w * h * 2));
    }

    private static int factorial(int a){
        int result = 1;
        while(a > 0){
            result *= a;
            a--;
        }
        return result;
    }

    private static int gcd(int a, int b){
        if(a == 0 || b == 0){
            return a + b;
        }
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        while(max % min != 0){
            int t = max - min;
            max = Math.max(t, min);
            min = Math.min(t, min);
        }
        return min;
    }

    private static int ticketSeller(int tickets, int value){
        return (int)(tickets * value * 0.72f);
    }

    private static int tables(int students, int table){
        if(students < table * 2){
            return 0;
        }
        return (students - (table * 2)) / 2 + ((students - (table * 2)) % 2 == 0 ? 0 : 1);
    }
}
