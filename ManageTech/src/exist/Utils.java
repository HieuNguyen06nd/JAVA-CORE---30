package exist;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public LocalDate inputDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng yyyy-MM-dd.");
            }
        }
    }

    public int inputInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Giá trị nhập vào không phải là số. Vui lòng nhập lại.");
            }
        }
    }

    public static String truncateString(String str, int maxWidth) {
        if (str.length() > maxWidth) {
            return str.substring(0, maxWidth - 3) + "..."; // Cắt bớt và thêm dấu "..."
        }
        return String.format("%-" + maxWidth + "s", str); // Căn trái và thêm khoảng trắng nếu cần
    }

    // Phương thức xuống dòng cho chuỗi dài
    public static String wrapString(String str, int maxWidth) {
        StringBuilder result = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i += maxWidth) {
            int end = Math.min(i + maxWidth, length);
            result.append(str, i, end).append("\n");
        }
        return result.toString().trim(); // Loại bỏ dòng trống cuối cùng
    }

}
