package data;

import entities.Course;
import entities.Student;
import entities.Teacher;
import entities.User;
import enums.Major;
import enums.Mode;
import enums.Role;
import service.AppContext;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    public void data(AppContext context){
        ArrayList<User>users = context.getUsers();
        users.add(new User("admin","Admin1.", "admin@gmail.com", Role.ADMIN));
        users.add(new Student("student","Admin1.", "student@gmail.com", Role.STUDENT, "12/12"));

        List<Major> majors= new ArrayList<>();
        majors.add(Major.CSS);
        users.add(new Teacher("teacher","Admin1.", "admin@gmail.com", Role.ADMIN,1000, "2 nam", "jajasj", majors));


//        course
        ArrayList<Course>courses = context.getCourses();
        courses.add(new Course("khoa hoc java", "haaops ahas", 1999, Mode.OFFLINE));
        courses.add(new Course("khoa hoc Back end", "haaops ahas", 2999, Mode.ONLINE));
        courses.add(new Course("khoa hoc font end", "haaops ahas", 1899, Mode.OFFLINE));




    }

}
