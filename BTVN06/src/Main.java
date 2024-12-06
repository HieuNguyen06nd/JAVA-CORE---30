import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentBiz[] studentBizs = null;
        StudentIt[] studentIts = null;
        TechStudent[] techStudents = null;

        while (true){
            System.out.println("====Menu=== \n" +
                    "1. Them sv it\n" +
                    "2. Them sv biz\n" +
                    "3. In sv tech\n" +
                    "4. In sv it\n" +
                    "5. In sv biz\n" +
                    "0. Thoat");
            System.out.println("Nhap lua chon");
            int choice =Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Nhap so sinh vien IT");
                    int n = Integer.parseInt(scanner.nextLine());
                    studentIts = new StudentIt[n];
                    for (int i = 0; i < studentIts.length; i++) {
                        studentIts[i] = inputStudentIt(scanner);
                    }
                    break;
                case 2:
                    System.out.println("Nhap so sinh vien BIZ");
                    n = Integer.parseInt(scanner.nextLine());
                    studentBizs = new StudentBiz[n];
                    for (int i = 0; i < studentBizs.length; i++) {
                        studentBizs[i] = inputStudentBiz(scanner);
                    }
                    break;
                case 3:
                    if (studentIts!= null && studentBizs != null){
                        techStudents = new TechStudent[studentIts.length+studentBizs.length];
                        for (int i = 0; i < studentIts.length; i++) {
                            techStudents[i] = studentIts[i];
                        }
                        for (int i = 0; i < studentBizs.length; i++) {
                            techStudents[studentIts.length+i] = studentBizs[i];
                        }
                        printInfo(techStudents);
                    }else {
                        System.out.println("chua co sv it hoac biz");
                    }

                    break;
                case 4:
                    printInfo(studentIts);
                    break;
                case 5:
                    printInfo(studentBizs);
                    break;
                case 0:
                    System.exit(1);
                default:
                    System.out.println("lua chon k hop le");

            }
        }

//        System.out.println("Nhap so sinh vien IT");
//        int n = Integer.parseInt(scanner.nextLine());
//        StudentIt[] studentIts = new StudentIt[n];
//        for (int i = 0; i < studentIts.length; i++) {
//            studentIts[i] = inputStudentIt(scanner);
//        }
//        System.out.println("Nhap so sinh vien BIZ");
//        n = Integer.parseInt(scanner.nextLine());
//        StudentBiz[] studentBizs = new StudentBiz[n];
//        for (int i = 0; i < studentBizs.length; i++) {
//            studentBizs[i] = inputStudentBiz(scanner);
//        }
//
//        TechStudent[] techStudents = new TechStudent[studentIts.length+studentBizs.length];
//        for (int i = 0; i < studentIts.length; i++) {
//            techStudents[i] = studentIts[i];
//        }
//        for (int i = 0; i < studentBizs.length; i++) {
//            techStudents[studentIts.length+i] = studentBizs[i];
//        }
//
//        printInfo(techStudents);


    }
    public static StudentIt inputStudentIt(Scanner scanner){
        System.out.println("nhap ten sv it");
        String name = scanner.nextLine();
        System.out.println("nhap nganh hoc");
        String study = scanner.nextLine();
        System.out.println("nhap diem java");
        double java = Double.parseDouble(scanner.nextLine());
        System.out.println("nhap diem html");
        double html = Double.parseDouble(scanner.nextLine());
        System.out.println("nhap diem css");
        double css = Double.parseDouble(scanner.nextLine());
        return new StudentIt(name,study,java,html,css);
    }
    public static StudentBiz inputStudentBiz(Scanner scanner){
        System.out.println("nhap ten sv it");
        String name = scanner.nextLine();
        System.out.println("nhap nganh hoc");
        String study = scanner.nextLine();
        System.out.println("nhap diem marketting");
        double marketting = Double.parseDouble(scanner.nextLine());
        System.out.println("nhap diem sales");
        double sales = Double.parseDouble(scanner.nextLine());
        return new StudentBiz(name,study,marketting,sales);
    }
    public static void printInfo(Object[] objects){
        for (Object i : objects){
            System.out.println(i);
        }
    }
}
