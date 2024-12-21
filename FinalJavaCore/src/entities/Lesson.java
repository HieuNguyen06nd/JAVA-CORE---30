package entities;

public class Lesson {
    private static int autoId;

    private String id;
    private String class_id;
    private String title;
    private String content;
//    Thứ tự bài học trong khóa.
    private String order;

    public Lesson( String class_id, String title, String content, String order) {
        this.id = "LESS" + ++autoId;
        this.class_id = class_id;
        this.title = title;
        this.content = content;
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
