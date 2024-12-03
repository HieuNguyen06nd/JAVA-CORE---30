package Bai02;

import java.util.Scanner;

public class Main {
    static int[] arr;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inputArray(scanner);
        int sum = sumArr(arr);
        System.out.println("Tong : " +sum);
    }
    public static void inputArray(Scanner scanner){
        System.out.println("moi nhap so Nguyen N");
        int n = Integer.parseInt(scanner.nextLine());
        arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            System.out.println("moi ban nhap so thu " + (i+1));
            arr[i]=Integer.parseInt(scanner.nextLine());
        }
    }
    public static int sumArr(int[] arr){
        int sum = 0;
        for(int i: arr){
            sum += i;
        }
        return sum;
    }
}
