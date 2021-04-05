
public class Main {

    public static void main(String[] args) {
        Factorial factorial = (num) -> {
            int temp = 1;
            for (int i = 2; i <= num; i++) {
                temp *= i;
            }
            System.out.println(temp);
         };


        Power power = (num1, num2) -> {
            int temp = 0;
            for (int i = 0; i <= num2; i++) {
                if (Math.pow(i, num1) == num2) {
                    temp = i;
                    break;
                }
            }
            System.out.println(temp);
        };


        factorial.factorial(5);
        power.power(3, 8);
    }
}

interface Factorial {
    void factorial(int num);
}

interface Power {
    void power(int num1, int num2);
}
