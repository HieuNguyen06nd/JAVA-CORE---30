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

public class Main {

    private static final String TEACHERS_FILE_PATH = "teachers.json";
    private static final String STUDENTS_FILE_PATH = "students.json";
    private static final String USER_FILE_PATH = "user.json";

    public static void main(String[] args) {
        AppContext appContext = AppContext.getInstance();

        try {
            initializeData(appContext);
        } catch (IOException e) {
            System.out.println("Lỗi khi khởi tạo dữ liệu: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        new Menu().displayMenu();
    }

    private static void initializeData(AppContext appContext) throws IOException {
        List<User> users = new ArrayList<>();

        List<Teacher> teachers = JsonHandler.readFromFile(TEACHERS_FILE_PATH, new TypeReference<>() {});
        users.addAll(teachers);

        List<Student> students = JsonHandler.readFromFile(STUDENTS_FILE_PATH, new TypeReference<>() {});
        users.addAll(students);

        List<User> admin = JsonHandler.readFromFile(USER_FILE_PATH, new TypeReference<>() {});
        users.add(new User("admin", "admin123", "admin@example.com", Role.ADMIN));

        appContext.setUsers(users);

        System.out.println("Danh sách users:");
        users.forEach(user -> System.out.println("Username: " + user.getUsername() + ", Role: " + user.getRole()));
    }
}