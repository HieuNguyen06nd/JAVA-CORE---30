package BTVN;

import java.util.Arrays;
import java.util.Comparator;

public class BTVN03 {
    public static void main(String[] args) {
        Student[] students = {
                new Student("Nguyễn Văn A", 18, 3),
                new Student("Hoàng Văn B", 17, 3),
                new Student("Trần Thị C", 18, 2),
                new Student("Lê Văn A", 19, 1),
                new Student("Phạm Văn D", 17, 4)
        };

//        #1. Sắp xếp học sinh theo fullName, nếu giống fullName thì ai nhiều tuổi hơn thì đứng trước.
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int nameCompare = s1.fullName.compareTo(s2.fullName);
                if (nameCompare == 0) {
                    return Integer.compare(s2.age, s1.age);
                }
                return nameCompare;
            }
        });

        System.out.println(Arrays.toString(students));

//      #2. Sắp xếp học sinh theo tuổi tăng dần, nếu bằng tuổi thì xếp theo GPA giảm dần
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int ageCompare = Integer.compare(s1.age, s2.age);
                if (ageCompare == 0) {
                    return Double.compare(s2.GPA, s1.GPA);
                }
                return ageCompare;
            }
        });

        System.out.println(Arrays.toString(students));
//      #3. Sắp xếp theo tên. (Ví dụ là Hoàng Văn A, thì chỉ xét A thôi)
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                String lastName1 = s1.fullName.substring(s1.fullName.lastIndexOf(' ') + 1);
                String lastName2 = s2.fullName.substring(s2.fullName.lastIndexOf(' ') + 1);
                return lastName1.compareTo(lastName2);
            }
        });

        System.out.println(Arrays.toString(students));
    }
}
