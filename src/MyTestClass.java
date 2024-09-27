import java.util.Scanner;

public class MyTestClass {
    public static void main(String[] args) {
        int[] unit = {50000, 10000, 1000, 100, 50, 10, 1};

        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();

        int count;

        for(int idx = 0; idx<unit.length; idx++){
            count = amount / unit[idx];
            System.out.println(unit[idx]+ " 원: "+count);
            amount %= unit[idx];
        }
    }
}
