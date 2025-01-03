package service;

import entities.User;
import enums.Role;
import view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    ValidateUser validateUser = new ValidateUser();

    public UserService() {
        // Constructor không tham số
    }

    public void signUp() {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        List<User> users = appContext.getList(User.class); // Lấy danh sách users từ AppContext

        // Nhập username
        String username;
        while (true) {
            System.out.print("Nhập Username: ");
            username = scanner.nextLine();
            if (!validateUser.existUsername(username, (ArrayList<User>) users)) {
                break; // Thoát vòng lặp nếu username hợp lệ
            } else {
                System.out.println("Username đã tồn tại, vui lòng nhập lại.");
            }
        }

        // Nhập mật khẩu
        String password;
        do {
            System.out.print("Nhập mật khẩu: ");
            password = scanner.nextLine();
            if (!validateUser.checkPassword(password)) {
                System.out.println("Mật khẩu không hợp lệ, vui lòng nhập lại.");
            }
        } while (!validateUser.checkPassword(password));

        // Nhập email
        String email;
        while (true) {
            System.out.print("Nhập email: ");
            email = scanner.nextLine();
            if (validateUser.existEmail(email, (ArrayList<User>) users)) {
                System.out.println("Email đã tồn tại, vui lòng nhập lại.");
            } else if (!validateUser.checkEmail(email)) {
                System.out.println("Email không hợp lệ, vui lòng nhập lại.");
            } else {
                break; // Thoát vòng lặp nếu email hợp lệ
            }
        }

        // Thêm người dùng mới
        users.add(new User(username, password, email, Role.STUDENT));
        System.out.println("Đăng ký thành công!");
    }

    public void signIn() {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        List<User> users = appContext.getList(User.class); // Lấy danh sách users từ AppContext

        User user = null;

        while (user == null) {
            System.out.print("Nhập username (hoặc nhập 'exit' để thoát): ");
            String username = scanner.nextLine().trim(); // Loại bỏ khoảng trắng thừa
            if (exitProgramme(username)) {
                return;
            }
            user = findUserByUsername(username, users); // Tìm user trong danh sách users
            if (user == null) {
                System.out.println("Kiểm tra lại username.");
            }
        }

        System.out.print("Nhập password: ");
        String password = scanner.nextLine();

        if (!user.getPassword().equals(password)) {
            System.out.println("Sai password");
            System.out.println("1 - Đăng nhập lại\n" +
                    "2 - Quên mật khẩu");
            System.out.println("Mời lựa chọn");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    signIn(); // Gọi lại phương thức signIn
                    break;
                case 2:
                    forgetPassword(user); // Gọi phương thức quên mật khẩu
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } else {
            System.out.println("Chào mừng " + user.getUsername() + ", bạn có thể thực hiện các công việc sau:");
            Menu menu = new Menu();
            menu.loginMenu(appContext, user); // Hiển thị menu đăng nhập
        }
    }

    public void forgetPassword(User user) {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        List<User> users = appContext.getList(User.class); // Lấy danh sách users từ AppContext

        System.out.print("Nhập email: ");
        String email = scanner.nextLine();

        User foundUser = findUserByEmail(email, users);
        if (foundUser == null) {
            System.out.println("Tài khoản không tồn tại.");
        } else {
            System.out.print("Nhập mật khẩu mới: ");
            String newPassword = scanner.nextLine();

            if (!validateUser.checkPassword(newPassword)) {
                System.out.println("Mật khẩu không hợp lệ.");
            } else {
                foundUser.setPassword(newPassword);
                System.out.println("Đổi mật khẩu thành công.");
            }
        }
    }

    public void changeUsername(User user) {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        List<User> users = appContext.getList(User.class); // Lấy danh sách users từ AppContext

        while (true) {
            System.out.print("Nhập username mới: ");
            String newUsername = scanner.nextLine();

            if (findUserByUsername(newUsername, users) != null) {
                System.out.println("Username đã tồn tại. Vui lòng nhập lại.");
            } else if (newUsername.equals(user.getUsername())) {
                System.out.println("Username mới không được trùng với username hiện tại. Vui lòng nhập lại.");
            } else {
                user.setUsername(newUsername);
                System.out.println("Thay đổi username thành công.");
                break;
            }
        }
    }

    public void changeEmail(User user) {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        List<User> users = appContext.getList(User.class); // Lấy danh sách users từ AppContext

        while (true) {
            System.out.print("Nhập email mới: ");
            String newEmail = scanner.nextLine();

            if (!validateUser.checkEmail(newEmail)) {
                System.out.println("Email không hợp lệ. Vui lòng thử lại.");
            } else if (validateUser.existEmail(newEmail, (ArrayList<User>) users)) {
                System.out.println("Email đã tồn tại. Vui lòng thử lại.");
            } else {
                user.setEmail(newEmail);
                System.out.println("Thay đổi email thành công.");
                break;
            }
        }
        Menu menu = new Menu();
        menu.loginMenu(appContext, user);
    }

    public void changePassword(User user) {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();

        while (true) {
            System.out.print("Nhập mật khẩu mới: ");
            String newPassword = scanner.nextLine();

            if (!validateUser.checkPassword(newPassword)) {
                System.out.println("Mật khẩu cần dài từ 7 đến 15 ký tự, bao gồm ít nhất 1 ký tự in hoa và 1 ký tự đặc biệt (. , - _ ;).");
            } else if (newPassword.equals(user.getPassword())) {
                System.out.println("Mật khẩu mới không được trùng với mật khẩu hiện tại. Vui lòng nhập lại.");
            } else {
                user.setPassword(newPassword);
                System.out.println("Thay đổi mật khẩu thành công.");
                break;
            }
        }
    }

    public void changeRole(User user) {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        System.out.println("Chọn role mới:");
        System.out.println("1 - Admin");
        System.out.println("2 - Teacher");
        System.out.println("3 - Student");
        int roleChoice = Integer.parseInt(scanner.nextLine());

        Role newRole = null;
        switch (roleChoice) {
            case 1:
                newRole = Role.ADMIN;
                break;
            case 2:
                newRole = Role.TEACHER;
                break;
            case 3:
                newRole = Role.STUDENT;
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }

        user.setRole(newRole);
        System.out.println("Role đã được cập nhật.");
    }

    public void deleteUser() {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        List<User> users = appContext.getList(User.class); // Lấy danh sách users từ AppContext

        System.out.print("Nhập id cần xóa: ");
        String id = scanner.nextLine().toUpperCase();
        User user = findById(id, users);
        if (user == null) {
            System.out.println("Không tồn tại user_id: " + id);
        } else {
            users.remove(user);
            System.out.println("Xóa thành công user_id: " + id);
        }
    }

    public User findUserByUsername(String username, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public User findUserByEmail(String email, List<User> users) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User findById(String id, List<User> users) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public boolean exitProgramme(String key) {
        if (key.equalsIgnoreCase("exit")) {
            System.out.println("Đã thoát khỏi chương trình.");
            return true;
        }
        return false;
    }
}