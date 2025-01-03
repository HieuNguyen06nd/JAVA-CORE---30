package data;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.Teacher;
import entities.Student;
import entities.User;
import enums.Role;
import service.AppContext;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UserManager {

    private final AppContext appContext;
    private final DataManager dataManager;

    public UserManager(AppContext appContext, DataManager dataManager) {
        this.appContext = appContext;
        this.dataManager = dataManager;
    }

    /**
     * Khởi tạo dữ liệu người dùng từ các file JSON.
     *
     * @throws IOException Nếu có lỗi khi đọc tệp.
     */
    public void initializeUsers() throws IOException {
        loadTeachers();
        loadStudents();
        loadUsers();
        ensureAdminUserExists();
        printUserList();
    }

    /**
     * Tải dữ liệu giáo viên từ file JSON.
     *
     * @throws IOException Nếu có lỗi khi đọc tệp.
     */
    private void loadTeachers() throws IOException {
        List<Teacher> teachers = dataManager.loadDataFromFile(
                "data/teachers.json",
                new TypeReference<List<Teacher>>() {}
        );
        appContext.getList(Teacher.class).addAll(teachers);
    }

    /**
     * Tải dữ liệu học sinh từ file JSON.
     *
     * @throws IOException Nếu có lỗi khi đọc tệp.
     */
    private void loadStudents() throws IOException {
        List<Student> students = dataManager.loadDataFromFile(
                "data/students.json",
                new TypeReference<List<Student>>() {}
        );
        appContext.getList(Student.class).addAll(students);
    }

    /**
     * Tải dữ liệu người dùng (Admin và Customer) từ file JSON.
     *
     * @throws IOException Nếu có lỗi khi đọc tệp.
     */
    private void loadUsers() throws IOException {
        List<User> users = dataManager.loadDataFromFile(
                "data/users.json",
                new TypeReference<List<User>>() {}
        );
        appContext.getList(User.class).addAll(users);
    }

    /**
     * Đảm bảo có ít nhất một người dùng admin trong hệ thống.
     */
    private void ensureAdminUserExists() {
        List<User> users = appContext.getList(User.class);
        if (users.stream().noneMatch(user -> user.getRole() == Role.ADMIN)) {
            users.add(new User("admin", "admin123", "admin@example.com", Role.ADMIN));
            System.out.println("Đã thêm người dùng admin mặc định.");
        }
    }

    /**
     * In danh sách người dùng để kiểm tra.
     */
    private void printUserList() {
        List<User> users = appContext.getList(User.class);
        System.out.println("Danh sách users:");
        users.forEach(user -> System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Role: " + user.getRole()));
    }

    /**
     * Lưu dữ liệu người dùng vào các file JSON trước khi thoát chương trình.
     *
     * @throws IOException Nếu có lỗi khi ghi tệp.
     */
    public void saveUsersBeforeExit() throws IOException {
        System.out.println("Đang lưu dữ liệu người dùng trước khi thoát...");

        List<User> users = appContext.getList(User.class);
        saveTeachers(users);
        saveStudents(users);
        saveAdminAndCustomers(users);

        System.out.println("Lưu dữ liệu người dùng thành công!");
    }

    /**
     * Lưu danh sách giáo viên vào file JSON.
     *
     * @param users Danh sách người dùng.
     * @throws IOException Nếu có lỗi khi ghi tệp.
     */
    private void saveTeachers(List<User> users) throws IOException {
        List<Teacher> teachers = users.stream()
                .filter(Teacher.class::isInstance)
                .map(Teacher.class::cast)
                .collect(Collectors.toList());
        dataManager.saveDataToFile("data/teachers.json", teachers);
    }

    /**
     * Lưu danh sách học sinh vào file JSON.
     *
     * @param users Danh sách người dùng.
     * @throws IOException Nếu có lỗi khi ghi tệp.
     */
    private void saveStudents(List<User> users) throws IOException {
        List<Student> students = users.stream()
                .filter(Student.class::isInstance)
                .map(Student.class::cast)
                .collect(Collectors.toList());
        dataManager.saveDataToFile("data/students.json", students);
    }

    /**
     * Lưu danh sách admin và customer vào file JSON.
     *
     * @param users Danh sách người dùng.
     * @throws IOException Nếu có lỗi khi ghi tệp.
     */
    private void saveAdminAndCustomers(List<User> users) throws IOException {
        List<User> adminAndCustomers = users.stream()
                .filter(user -> user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER)
                .collect(Collectors.toList());
        dataManager.saveDataToFile("data/users.json", adminAndCustomers);
    }
}