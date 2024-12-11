package techmaster;

import techmaster.entities.Student;
import techmaster.service.StudentService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();
        // Khởi tạo ra một danh sách các hs trong lớp
        ArrayList<Student> students = new ArrayList<>();
        do {
            System.out.println("1- Hiển thị thông tin chi tiết của trung tâm\n" +
                    "2- Thực hiện thêm học viên vào lớp hiện tại\n" +
                    "3- Thực hiện cập nhật thông tin học lực của 1b học viên dựa vào Id\n" +
                    "4- Có 1 bạn xin nghỉ học, hãy giúp thầy xóa bạn đó khỏi lớp học\n");
            System.out.println("Mời bạn lựa chọn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose){
                case 1:
                    System.out.println(students);
                    break;
                case 2:
                    Student student = studentService.inputStudent(scanner);
                    students.add(student);
                    System.out.println(students);
                    break;
                case 3:
                    studentService.updateHocLuc(scanner,students);
                    break;
                case 4:
                    studentService.deleteStudent(scanner,students);
                    break;
                default:
                    System.exit(0);
            }
        } while (true);

    }


}
