package service;

import entities.Classes;
import entities.Lesson;
import entities.User;
import exist.Exist;
import exist.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LessonService {
    Exist exist = new Exist();
    Utils utils = new Utils();

    public void inputLesson(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        List<Lesson> lessons = appContext.getList(Lesson.class);
        List<Classes> classes = appContext.getList(Classes.class);

        String classId;
        while (true) {
            System.out.print("Nhập class_id: ");
            classId = scanner.nextLine();
            if (!exist.isClassExist(classId, (ArrayList<Classes>) classes)) {
                System.out.println("Class_id không tồn tại. Vui lòng nhập lại.");
            } else {
                break;
            }
        }

        System.out.print("Nhập title bài học: ");
        String title = scanner.nextLine();

        System.out.print("Nhập nội dung bài học: ");
        String content = scanner.nextLine();

        int order;
        while (true) {
            System.out.print("Nhập thứ tự bài học: ");
            try {
                order = Integer.parseInt(scanner.nextLine());

                if (exist.isOrderExist(classId, order, lessons)) {
                    System.out.println("Thứ tự bài học đã tồn tại trong lớp. Vui lòng nhập lại.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Thứ tự bài học không hợp lệ. Vui lòng nhập lại.");
            }
        }

        Lesson lesson = new Lesson(classId,title, content, order);
        lessons.add(lesson);
        System.out.println("Bài học đã được thêm thành công!");
    }

    public void changeLesson(AppContext appContext, User currentUser) {
        Scanner scanner = appContext.getScanner();
        List<Lesson> lessons = appContext.getList(Lesson.class);
        List<Classes> classes = appContext.getList(Classes.class);

        System.out.print("Nhập id bài học muốn sửa: ");
        String id = scanner.nextLine();

        Lesson lessonToUpdate = findById(id, lessons);
        if (lessonToUpdate == null) {
            System.out.println("Không tìm thấy bài học với id: " + id);
            return;
        }

        // Tìm lớp học mà bài học thuộc về
        Classes classOfLesson = findByIdClass(lessonToUpdate.getClass_id(), classes);
        if (classOfLesson == null) {
            System.out.println("Không tìm thấy lớp học của bài học này.");
            return;
        }

        // Kiểm tra xem giáo viên hiện tại có phải là giáo viên phụ trách lớp không
        if (!classOfLesson.getTeacher_id().equals(currentUser.getId())) {
            System.out.println("Bạn không có quyền sửa bài học trong lớp này.");
            return;
        }

        while (true) {
            System.out.println("2. Thay đổi title");
            System.out.println("3. Thay đổi nội dung");
            System.out.println("4. Thay đổi thứ tự");
            System.out.println("5. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = utils.inputInt(scanner, "Chọn chức năng: ");

            switch (choice) {
                case 2:
                    // Thay đổi title
                    System.out.print("Nhập title mới: ");
                    String newTitle = scanner.nextLine();
                    lessonToUpdate.setTitle(newTitle);
                    System.out.println("Thay đổi title thành công.");
                    break;
                case 3:
                    // Thay đổi nội dung
                    System.out.print("Nhập nội dung mới: ");
                    String newContent = scanner.nextLine();
                    lessonToUpdate.setContent(newContent);
                    System.out.println("Thay đổi nội dung thành công.");
                    break;
                case 4:
                    // Thay đổi thứ tự
                    int newOrder;
                    while (true) {
                        System.out.print("Nhập thứ tự mới: ");
                        try {
                            newOrder = Integer.parseInt(scanner.nextLine());
                            lessonToUpdate.setOrder(newOrder);
                            System.out.println("Thay đổi thứ tự thành công.");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Thứ tự không hợp lệ. Vui lòng nhập lại.");
                        }
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    public void deleteLesson(AppContext appContext, User currentUser) {
        Scanner scanner = appContext.getScanner();
        List<Lesson> lessons = appContext.getList(Lesson.class);
        List<Classes> classes = appContext.getList(Classes.class);

        System.out.println("Danh sách bài học hiện tại: ");
        for (Lesson lesson : lessons) {
            System.out.println("ID: " + lesson.getId() + ", Class ID: " + lesson.getClass_id() + ", Nội dung: " + lesson.getContent());
        }

        System.out.print("Nhập ID của bài học cần xóa: ");
        String lessonId = scanner.nextLine();

        Lesson lessonToDelete = findById(lessonId, lessons);

        if (lessonToDelete == null) {
            System.out.println("Không tìm thấy bài học với ID: " + lessonId);
        } else {
            // Tìm lớp học mà bài học thuộc về
            Classes classOfLesson = findByIdClass(lessonToDelete.getClass_id(), classes);
            if (classOfLesson == null) {
                System.out.println("Không tìm thấy lớp học của bài học này.");
                return;
            }

            // Kiểm tra xem giáo viên hiện tại có phải là giáo viên phụ trách lớp không
            if (!classOfLesson.getTeacher_id().equals(currentUser.getId())) {
                System.out.println("Bạn không có quyền xóa bài học trong lớp này.");
                return;
            }

            lessons.remove(lessonToDelete);
            System.out.println("Bài học với ID " + lessonId + " đã được xóa.");
        }
    }

    public void swapLessonOrders(AppContext appContext, User currentUser) {
        List<Lesson> lessons = appContext.getList(Lesson.class);
        Scanner scanner = appContext.getScanner();
        List<Classes> classes = appContext.getList(Classes.class);

        System.out.print("Nhập class_id của lớp học mà bạn muốn hoán đổi bài học: ");
        String classId = scanner.nextLine();

        // Tìm lớp học
        Classes classOfLesson = findByIdClass(classId, classes);
        if (classOfLesson == null) {
            System.out.println("Lớp học không tồn tại: " + classId);
            return;
        }

        // Kiểm tra xem giáo viên hiện tại có phải là giáo viên phụ trách lớp không
        if (!classOfLesson.getTeacher_id().equals(currentUser.getId())) {
            System.out.println("Bạn không có quyền hoán đổi bài học trong lớp này.");
            return;
        }

        System.out.println("Danh sách bài học trong lớp " + classId + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId)) {
                System.out.println("ID: " + lesson.getId() + ", Thứ tự: " + lesson.getOrder() + ", Nội dung: " + lesson.getContent());
            }
        }

        System.out.print("Nhập ID bài học thứ nhất cần hoán đổi: ");
        String firstLessonId = scanner.nextLine();
        System.out.print("Nhập ID bài học thứ hai cần hoán đổi: ");
        String secondLessonId = scanner.nextLine();

        // Tìm bài học theo ID
        Lesson firstLesson = findById(firstLessonId, lessons);
        Lesson secondLesson = findById(secondLessonId, lessons);

        if (firstLesson == null || secondLesson == null) {
            System.out.println("Không tìm thấy một trong hai bài học. Vui lòng kiểm tra lại ID.");
            return;
        }

        if (!firstLesson.getClass_id().equals(classId) || !secondLesson.getClass_id().equals(classId)) {
            System.out.println("Các bài học phải thuộc cùng một lớp học.");
            return;
        }

        int tempOrder = firstLesson.getOrder();
        firstLesson.setOrder(secondLesson.getOrder());
        secondLesson.setOrder(tempOrder);

        System.out.println("Đã hoán đổi thứ tự giữa bài học " + firstLessonId + " và bài học " + secondLessonId);
    }



    public  Lesson findById(String id, List<Lesson>lessons){
        for (Lesson lesson:lessons){
            if (lesson.getId().equalsIgnoreCase(id)){
                return lesson;
            }
        }
        return null;
    }

    public Classes findByIdClass(String id, List<Classes> classes) {
        for (Classes classRoom : classes) {
            if (classRoom.getId().equals(id)) {
                return classRoom;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }


}
