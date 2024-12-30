package data;

import entities.User;
import enums.Role;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data {

    // Ghi danh sách User vào file JSON
    public void writeUsersToJsonFile(List<User> users, String filePath) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            jsonBuilder.append("  {\n");
            jsonBuilder.append("    \"id\": \"").append(user.getId()).append("\",\n");
            jsonBuilder.append("    \"username\": \"").append(user.getUsername()).append("\",\n");
            jsonBuilder.append("    \"password\": \"").append(user.getPassword()).append("\",\n");
            jsonBuilder.append("    \"email\": \"").append(user.getEmail()).append("\",\n");
            jsonBuilder.append("    \"role\": \"").append(user.getRole()).append("\",\n");
            jsonBuilder.append("    \"budget\": ").append(user.getBudget()).append(",\n");
            jsonBuilder.append("    \"created_at\": \"").append(user.getCreated_at()).append("\"\n");
            jsonBuilder.append("  }");

            // Thêm dấu phẩy nếu không phải là phần tử cuối cùng
            if (i < users.size() - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }

        jsonBuilder.append("]"); // Kết thúc mảng JSON

        // Ghi chuỗi JSON vào file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonBuilder.toString());
            System.out.println("Dữ liệu đã được ghi vào file JSON thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<User> readUsersFromJsonFile(String filePath) {
        List<User> users = new ArrayList<>();
        StringBuilder jsonBuilder = new StringBuilder();

        // Đọc toàn bộ nội dung file JSON
        try (FileReader reader = new FileReader(filePath)) {
            int character;
            while ((character = reader.read()) != -1) {
                jsonBuilder.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Phân tích chuỗi JSON
        String jsonString = jsonBuilder.toString().replaceAll("\\s", ""); // Loại bỏ khoảng trắng
        String[] userStrings = jsonString.substring(1, jsonString.length() - 1).split("\\},\\{"); // Tách các đối tượng User

        for (String userString : userStrings) {
            userString = userString.replace("{", "").replace("}", ""); // Loại bỏ dấu ngoặc nhọn
            String[] fields = userString.split(","); // Tách các trường

            String id = null, username = null, password = null, email = null, role = null;
            BigDecimal budget = null;
            LocalDate created_at = null;

            for (String field : fields) {
                String[] keyValue = field.split(":");
                String key = keyValue[0].replace("\"", ""); // Loại bỏ dấu ngoặc kép
                String value = keyValue[1].replace("\"", ""); // Loại bỏ dấu ngoặc kép

                switch (key) {
                    case "id":
                        id = value;
                        break;
                    case "username":
                        username = value;
                        break;
                    case "password":
                        password = value;
                        break;
                    case "email":
                        email = value;
                        break;
                    case "role":
                        role = value;
                        break;
                    case "budget":
                        budget = new BigDecimal(value);
                        break;
                    case "created_at":
                        created_at = LocalDate.parse(value);
                        break;
                }
            }

            if (id != null && username != null && password != null && email != null && role != null && budget != null && created_at != null) {
                users.add(new User(id, username, password, email, Role.valueOf(role), budget, created_at));
            }
        }

        System.out.println("Dữ liệu đã được đọc từ file JSON thành công!");
        return users;
    }
}