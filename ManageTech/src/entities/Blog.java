package entities;

import java.time.LocalDate;

public class Blog {
    private static int autoId;
    private String id;
    private String title;
    private String content;
    //Tác giả của bài viết.
    private String user_Id;
    //    Thời gian xuất bản.
    private LocalDate published_at;
    private boolean status;

    public Blog(String title, String content, String user_Id) {
        this.id = generateId();
        this.title = title;
        this.content = content;
        this.user_Id = user_Id;
        this.published_at =LocalDate.now();
        this.status = false;
    }

    public Blog() {
        this.id = generateId(); // Tạo ID tự động ngay cả khi sử dụng constructor không tham số
    }

    private String generateId() {
        return "BLOG" + ++autoId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public LocalDate getPublished_at() {
        return published_at;
    }

    public void setPublished_at(LocalDate published_at) {
        this.published_at = published_at;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
