import data.JsonHandler;
import entities.Teacher;
import entities.Student;
import entities.User;
import enums.Role;
import service.AppContext;
import view.Menu;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String TEACHERS_FILE_PATH = "teachers.json";
    private static final String STUDENTS_FILE_PATH = "students.json";
    private static final String USERS_FILE_PATH = "users.json"; // File chứa Admin và Customer
    private static final String COURSES_FILE_PATH = "courses.json";
    private static final AppContext appContext = AppContext.getInstance();

    public static void main(String[] args) {
        // Đăng ký Shutdown Hook để lưu dữ liệu trước khi thoát
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                saveDataBeforeExit();
            } catch (IOException e) {
                System.err.println("Lỗi khi lưu dữ liệu trước khi thoát: " + e.getMessage());
            }
        }));

        try {
            // Khởi tạo dữ liệu từ các tệp JSON
            initializeData(appContext);
        } catch (IOException e) {
            System.out.println("Lỗi khi khởi tạo dữ liệu: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Hiển thị menu chính
        new Menu().displayMenu();
    }

    /**
     * Khởi tạo dữ liệu từ các tệp JSON.
     *
     * @param appContext Đối tượng AppContext để lưu trữ dữ liệu.
     * @throws IOException Nếu có lỗi khi đọc tệp.
     */
    private static void initializeData(AppContext appContext) throws IOException {
        List<User> users = new ArrayList<>();

        // Đọc danh sách giáo viên từ tệp teachers.json
        if (JsonHandler.isFileReadable(TEACHERS_FILE_PATH)) {
            List<Teacher> teachers = JsonHandler.readFromFile(TEACHERS_FILE_PATH, new TypeReference<>() {});
            if (teachers != null) {
                teachers.removeIf(teacher -> teacher.getUsername() == null || teacher.getUsername().isEmpty());
                users.addAll(teachers);
            }
        } else {
            System.out.println("File " + TEACHERS_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(TEACHERS_FILE_PATH, new ArrayList<Teacher>(), true);
        }

        // Đọc danh sách học sinh từ tệp students.json
        if (JsonHandler.isFileReadable(STUDENTS_FILE_PATH)) {
            List<Student> students = JsonHandler.readFromFile(STUDENTS_FILE_PATH, new TypeReference<>() {});
            if (students != null) {
                students.removeIf(student -> student.getUsername() == null || student.getUsername().isEmpty());
                users.addAll(students);
            }
        } else {
            System.out.println("File " + STUDENTS_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(STUDENTS_FILE_PATH, new ArrayList<Student>(), true);
        }

        // Đọc danh sách người dùng (Admin và Customer) từ tệp users.json
        if (JsonHandler.isFileReadable(USERS_FILE_PATH)) {
            List<User> adminAndCustomers = JsonHandler.readFromFile(USERS_FILE_PATH, new TypeReference<>() {});
            if (adminAndCustomers != null) {
                adminAndCustomers.removeIf(user -> user.getUsername() == null || user.getUsername().isEmpty());
                users.addAll(adminAndCustomers);
            }
        } else {
            System.out.println("File " + USERS_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(USERS_FILE_PATH, new ArrayList<User>(), true);
        }

        // Thêm người dùng admin cứng nếu chưa tồn tại
        if (users.stream().noneMatch(user -> user.getRole() == Role.ADMIN)) {
            users.add(new User("admin", "admin123", "admin@example.com", Role.ADMIN));
        }

        // Lưu danh sách người dùng vào AppContext
        appContext.setUsers(users);

        // In danh sách người dùng để kiểm tra
        System.out.println("Danh sách users:");
        users.forEach(user -> System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Role: " + user.getRole()));
    }

    /**
     * Lưu dữ liệu vào các tệp JSON trước khi thoát chương trình.
     *
     * @throws IOException Nếu có lỗi khi ghi tệp.
     */
    private static void saveDataBeforeExit() throws IOException {
        System.out.println("Đang lưu dữ liệu trước khi thoát...");

        // Lấy danh sách người dùng từ AppContext
        List<User> users = appContext.getUsers();

        // Tách danh sách giáo viên, học sinh, admin và customer
        List<Teacher> teachers = users.stream()
                .filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user)
                .collect(Collectors.toList());

        List<Student> students = users.stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .collect(Collectors.toList());

        List<User> adminAndCustomers = users.stream()
                .filter(user -> user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER)
                .collect(Collectors.toList());

        // Ghi danh sách giáo viên vào teachers.json (nếu không rỗng)
        if (!teachers.isEmpty()) {
            JsonHandler.writeToFile(TEACHERS_FILE_PATH, teachers, true);
        } else {
            System.out.println("Danh sách giáo viên trống, không ghi vào tệp.");
        }

        // Ghi danh sách học sinh vào students.json (nếu không rỗng)
        if (!students.isEmpty()) {
            JsonHandler.writeToFile(STUDENTS_FILE_PATH, students, true);
        } else {
            System.out.println("Danh sách học sinh trống, không ghi vào tệp.");
        }

        // Ghi danh sách admin và customer vào users.json (nếu không rỗng)
        if (!adminAndCustomers.isEmpty()) {
            JsonHandler.writeToFile(USERS_FILE_PATH, adminAndCustomers, true);
        } else {
            System.out.println("Danh sách admin và customer trống, không ghi vào tệp.");
        }

        System.out.println("Lưu dữ liệu thành công!");
    }
}