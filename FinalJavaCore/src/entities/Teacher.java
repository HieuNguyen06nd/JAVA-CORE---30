package entities;

import enums.Role;

public class Teacher extends User{

//    Số năm kinh nghiệm.
    private int experience;
//    Mô tả chi tiết về giảng viên.
    private String bio;

    public Teacher(String email, String username, String phone, String password, int experience, String bio) {
        super(email, username, phone, password, Role.TEACHER);
        this.experience = experience;
        this.bio = bio;
    }
}
