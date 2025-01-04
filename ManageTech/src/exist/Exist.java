package exist;

import entities.*;
import enums.Role;
import service.AppContext;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Exist {
    private AppContext context= AppContext.getInstance();

    public boolean isClassExist(String classId, List<Classes> classRooms) {
        for (Classes classes : classRooms) {
            if (classes.getId().equals(classId)) {
                return true;
            }
        }
        return false;
    }
    public boolean isOrderExist(String classId, int order, List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId) && lesson.getOrder() == order) {
                return true;
            }
        }
        return false;
    }

    public boolean isCourseExist(String courseId, AppContext appContext) {
        List<Course> courses = appContext.getList(Course.class);

        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isStudentInClass(String studentId, AppContext appContext) {
        List<Classes> classes = appContext.getList(Classes.class);

        for (Classes classItem : classes) {
            if (classItem.getStudent_id().contains(studentId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isStudent(String studentId, AppContext appContext) {
        List<User> users = appContext.getList(User.class);

        for (User user : users) {
            if (user.getId().equals(studentId)) {
                if (user.getRole().equals(Role.STUDENT)) {
                    return true;
                } else {
                    System.out.println("User với ID " + studentId + " không phải là STUDENT.");
                    return false;
                }
            }
        }
        System.out.println("Không tìm thấy user với ID " + studentId);
        return false;
    }

    public boolean isValidRole(String id,AppContext appContext, Role role) {
        List<User> users = appContext.getList(User.class);
        for (User user : users) {
            if (user.getId().equals(id) && user.getRole().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAlreadyEnrolled(String userId, String courseId, List<Enrollments> enrollments) {
        for (Enrollments enrollment : enrollments) {
            if (enrollment.getUser_id().equals(userId) && enrollment.getCourse_id().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isScheduleConflict(Classes classRoom, String newSchedule, List<Classes> classes) {
        for (Classes otherClass : classes) {
            if (!otherClass.getId().equals(classRoom.getId())) {
                if (otherClass.getSchedule().equals(newSchedule)) {
                    if (otherClass.getTeacher_id().equals(classRoom.getTeacher_id())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isUserHasCard(String user_id) {
        List<ATMCard> atmCards = context.getList(ATMCard.class);
        for (ATMCard card : atmCards) {
            if (card.getUser_id().equals(user_id)) {
                return true; // Người dùng đã có thẻ
            }
        }
        return false; // Người dùng chưa có thẻ
    }

    public boolean isCardNumberUnique(String cardNumber) {
        List<ATMCard> atmCards = context.getList(ATMCard.class);
        for (ATMCard card : atmCards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return false; // Số thẻ đã tồn tại
            }
        }
        return true; // Số thẻ là duy nhất
    }

    public boolean isValidCardNumber(String cardNumber) {
        // Biểu thức chính quy: chỉ chứa số và có độ dài từ 12 đến 16 ký tự
        return cardNumber.matches("\\d{12,16}");
    }

    // Phương thức kiểm tra mã PIN chỉ chứa 4 chữ số
    public boolean isValidPin(String pin) {
        // Biểu thức chính quy: chỉ chứa 4 chữ số
        return pin.matches("\\d{4}");
    }

    public boolean isValidExpiryDate(String expiryDate) {
        // Biểu thức chính quy: định dạng MM/yy
        if (!expiryDate.matches("\\d{2}/\\d{2}")) {
            return false; // Không đúng định dạng
        }

        // Phân tích ngày hết hạn
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth expiry = YearMonth.parse(expiryDate, formatter);

            // Lấy ngày hiện tại
            YearMonth current = YearMonth.now();

            // Kiểm tra xem ngày hết hạn có lớn hơn hoặc bằng ngày hiện tại không
            return !expiry.isBefore(current);
        } catch (DateTimeParseException e) {
            return false; // Ngày hết hạn không hợp lệ
        }
    }


}

