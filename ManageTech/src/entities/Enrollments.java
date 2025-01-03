package entities;

public class Enrollments {
    private static int autoId;

    private String id;
    private String user_id;
    private String course_id;

    private int status;

    public Enrollments( String user_id, String course_id, int status) {
        this.id =generateId();
        this.user_id = user_id;
        this.course_id = course_id;
        this.status = status;
    }

    public Enrollments() {
        this.id = generateId(); // Tạo ID tự động ngay cả khi sử dụng constructor không tham số
    }

    private String generateId() {
        return "EN" + ++autoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
