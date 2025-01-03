package service;

import entities.Teacher;
import enums.Major;
import enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherService {
    public Teacher inputTeacher() {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        List<Teacher> teachers = appContext.getList(Teacher.class);

        String username;
        while (true) {
            System.out.print("Nhập Username cho giảng viên: ");
            username = scanner.nextLine();
            boolean exists = false;
            for (Teacher teacher : teachers) {
                if (teacher.getUsername().equals(username)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                break;
            } else {
                System.out.println("Username đã tồn tại, vui lòng nhập lại.");
            }
        }

        String password;
        do {
            System.out.print("Nhập mật khẩu: ");
            password = scanner.nextLine();
            if (password.length() < 6) {
                System.out.println("Mật khẩu phải có ít nhất 6 ký tự.");
            }
        } while (password.length() < 6);

        String email;
        while (true) {
            System.out.print("Nhập email cho giảng viên: ");
            email = scanner.nextLine();
            if (email.contains("@") && email.contains(".")) {
                break;
            } else {
                System.out.println("Email không hợp lệ. Vui lòng thử lại.");
            }
        }

        double salary;
        while (true) {
            System.out.print("Nhập lương của giảng viên: ");
            try {
                salary = Double.parseDouble(scanner.nextLine());
                if (salary > 0) {
                    break;
                } else {
                    System.out.println("Lương phải lớn hơn 0. Vui lòng thử lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lương không hợp lệ, vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập kinh nghiệm giảng dạy: ");
        String experience = scanner.nextLine();

        System.out.print("Nhập tiểu sử giảng viên: ");
        String bio = scanner.nextLine();

        List<Major> selectedMajors = selectMajors();

        Teacher teacher = new Teacher(username, password, email, Role.TEACHER, salary, experience, bio, selectedMajors);
        return teacher;
    }
    private List<Major> selectMajors() {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        List<Major> selectedMajors = new ArrayList<>();

        System.out.println("Chọn chuyên môn (chọn nhiều chuyên môn nếu muốn, nhập 'done' để kết thúc):");
        System.out.println("1 - HTML");
        System.out.println("2 - JAVA");
        System.out.println("3 - PHP");
        System.out.println("4 - CSS");
        System.out.println("Nhập số tương ứng với chuyên môn hoặc 'done' để hoàn tất.");

        while (true) {
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("done")) {
                break;
            }
            try {
                int option = Integer.parseInt(choice);
                Major major = getMajorByChoice(option);
                if (major != null && !selectedMajors.contains(major)) {
                    selectedMajors.add(major);
                    System.out.println("Chuyên môn " + major + " đã được thêm.");
                } else if (selectedMajors.contains(major)) {
                    System.out.println("Chuyên môn này đã được chọn.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }

        return selectedMajors;
    }

    private Major getMajorByChoice(int choice) {
        switch (choice) {
            case 1:
                return Major.HTML;
            case 2:
                return Major.JAVA;
            case 3:
                return Major.PHP;
            case 4:
                return Major.CSS;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return null;
        }
    }

    public double calculateSalary(Teacher teacher) {
        double totalSalary = teacher.getSalary();

        // Thêm lương theo kinh nghiệm
        int yearsOfExperience = Integer.parseInt(teacher.getExperience().split(" ")[0]);
        double bonusByExperience = yearsOfExperience * 1000;  // Giả sử mỗi năm kinh nghiệm thêm 1000
        totalSalary += bonusByExperience;

        return totalSalary;
    }
    public void printTeacherSalary(Teacher teacher) {
        double salary = calculateSalary(teacher);
        System.out.println("Lương của giảng viên " + teacher.getUsername() + " là: " + salary);
    }

}
