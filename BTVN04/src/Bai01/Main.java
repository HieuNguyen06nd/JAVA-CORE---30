package Bai01;

import java.util.Scanner;

public class Main {
    static int rowA, rowB, colunmA, colunmB;
    static int[][] arrA;
    static int[][] arrB;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inputArray(scanner);
        System.out.println("array A");
        printArray(arrA);
        System.out.println("array B");
        printArray(arrB);
        if (rowA == rowB && colunmA == colunmB) {
            int[][] sumArr = sumArray(arrA, arrB);
            System.out.println("Array sum:");
            printArray(sumArr);
        } else {
            System.out.println("khong the thuc hien");
        }
    }
    public static void inputArray(Scanner scanner){
//        Input Array A
        System.out.print("Nhap row A: ");
        rowA = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhap colunm A: ");
        colunmA = Integer.parseInt(scanner.nextLine());
        arrA = new int[rowA][colunmA];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colunmA ; j++) {
                System.out.print("["+i + "]" + "["+ j+ "]" + "\t");
                arrA[i][j] = Integer.parseInt(scanner.nextLine());
            }
        }
//        Input Array B
        System.out.print("Nhap row B: ");
        rowB = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhap colunm B: ");
        colunmB = Integer.parseInt(scanner.nextLine());

        arrB = new int[rowB][colunmB];
        for (int i = 0; i < rowB; i++) {
            for (int j = 0; j <colunmB ; j++) {
                System.out.print("["+i + "]" + "["+ j+ "]"+ "\t");
                arrB[i][j] = Integer.parseInt(scanner.nextLine());
            }
        }
    }
    public static int[][] sumArray(int[][] arr1, int[][] arr2){
        int[][] sumArr= new  int[rowA][colunmA];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colunmA; j++) {
                sumArr[i][j] = arr1[i][j]+arr2[i][j];
            }
        }
        return sumArr;
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
