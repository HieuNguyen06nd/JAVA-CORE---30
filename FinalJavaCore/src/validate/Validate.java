package validate;

import entities.User;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPhone(String phone) {
        String phoneRegex = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    // Phương thức kiểm tra mật khẩu hợp lệ (ít nhất 8 ký tự, bao gồm chữ cái và số)
    public boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean existEmail(String email, ArrayList<User> users){
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Email đã tồn tại. Vui lòng chọn email khác.");
                break;
            }
        }
        return false;
    }
    public boolean existUsername(String username, ArrayList<User> users){
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username đã tồn tại. Vui lòng chọn username khác.");
                break;
            }
        }
        return false;
    }
}
