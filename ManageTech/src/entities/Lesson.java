package entities;

import java.util.HashMap;
import java.util.Map;

public class Lesson {
    private static int autoId;

    private String id;
    private String class_id;
    private String title;
    private String content;
    private int order;


    public Lesson( String class_id,String title, String content, int order) {
        this.id =generateId();
        this.class_id = class_id;
        this.title = title;
        this.content = content;
        this.order = order;
    }

    public Lesson() {
        this.id = generateId(); // Tạo ID tự động ngay cả khi sử dụng constructor không tham số
    }

    private String generateId() {
        return "LESS" + ++autoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
