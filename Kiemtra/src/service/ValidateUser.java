package service;

import entities.User;

import java.util.ArrayList;
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

    public void existEmail(String email, ArrayList<User> users){
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Email đã tồn tại. Vui lòng chọn email khác.");
                break;
            }
        }
    }
    public void existUsername(String username, ArrayList<User> users){
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                System.out.println("Username đã tồn tại. Vui lòng chọn username khác.");
                break;
            }
        }
    }
}
