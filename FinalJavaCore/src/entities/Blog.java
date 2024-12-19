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



}
