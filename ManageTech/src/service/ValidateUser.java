package service;

import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUser {

    public boolean checkPassword(String password){
        if (password.length() <7 || password.length() >15){
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*[\\.,\\-_;].*")) {
            return false;
        }
        return true;
    }

    public boolean checkEmail(String email){
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean existEmail(String email, List<User> users) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public boolean existUsername(String username, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true; // Trả về true nếu username đã tồn tại
            }
        }
        return false; // Trả về false nếu username chưa tồn tại
    }

}
