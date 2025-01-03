//package data;
//
//import entities.*;
//import enums.Major;
//import enums.Mode;
//import enums.Role;
//import service.AppContext;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class DataService {
//    public void data(AppContext context){
//        ArrayList<User>users = context.getUsers();
////        KH1
//        users.add(new User("admin","Admin1.", "admin@gmail.com", Role.ADMIN));
////        KH2
//        users.add(new Student("student","Admin1.", "student@gmail.com", Role.STUDENT, "12/12"));
//
//        List<Major> majors= new ArrayList<>();
//        majors.add(Major.CSS);
////        KH3
//        users.add(new Teacher("teacher","Admin1.", "admin@gmail.com", Role.TEACHER,1000, "2", "jajasj", majors));
////      KH4
//        users.add(new Student("student1","Admin1.", "student@gmail.com", Role.STUDENT, "12/12"));
////        KH5
//        users.add(new Student("student2","Admin1.", "student@gmail.com", Role.STUDENT, "12/12"));
//
//        users.add(new Teacher("teacher2","Admin1.", "admin@gmail.com", Role.TEACHER,1000, "2", "jajasj", majors));
//
////        course
//        ArrayList<Course>courses = context.getCourses();
////        COU1
//        courses.add(new Course("khoa hoc java", "haaops ahas",  new BigDecimal("19209"), Mode.OFFLINE));
////        COU2
//        courses.add(new Course("khoa hoc Back end", "haaops ahas",  new BigDecimal("1929"), Mode.ONLINE));
////        COU3
//        courses.add(new Course("khoa hoc font end", "haaops ahas",  new BigDecimal("1929"), Mode.OFFLINE));
//
////        class
//        List<String>studentList = new ArrayList<>();
//        studentList.add("KH2");
//        ArrayList<Classes> classes = context.getClasses();
//        String input = "2024-12-02";
//        String input2 = "2024-12-22";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        LocalDate startDate = LocalDate.parse(input, formatter);
//        LocalDate endDate = LocalDate.parse(input2, formatter);
////      CLASS1
//        classes.add(new Classes("Class java", "COU1", "KH3",studentList,startDate, endDate,"1"));
//        classes.add(new Classes("Class html", "COU1", "KH3",studentList,startDate, endDate,"1"));
//        classes.add(new Classes("Class css", "COU2", "KH6",studentList,startDate, endDate,"1"));
//
//
//// lesson
//        ArrayList<Lesson>lessons =context.getLessons();
////        LESS1
//        lessons.add(new Lesson("CLASS1", "Bai giang 1", "noi dung bai gianrg", 1));
//        lessons.add(new Lesson("CLASS1", "Bai giang 2", "noi dung bai gianr22", 2));
//        lessons.add(new Lesson("CLASS2", "Bai giang 1", "noi dung bai gianrg", 1));
//
////       Blog
//        ArrayList<Blog>blogs = context.getBlogs();
////        BLOG1
//        blogs.add(new Blog("blog 1", "noi dung blog", "KH1"));
//
//
//        ArrayList<Score>scores = context.getScores();
//        scores.add(new Score("KH2", "LESS1", 9, "KH3", LocalDate.now(), null));
//        scores.add(new Score("KH4", "LESS1", 9, "KH3", LocalDate.now(), null));
//        scores.add(new Score("KH5", "LESS1", 9, "KH3", LocalDate.now(), null));
//        scores.add(new Score("KH2", "LESS3", 9, "KH6", LocalDate.now(), null));
//    }
//
//}
