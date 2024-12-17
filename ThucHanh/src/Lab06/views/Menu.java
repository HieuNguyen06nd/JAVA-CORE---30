package Lab06.views;

import Lab06.entities.Person;
import Lab06.service.PersonService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    PersonService personService = new PersonService();


    public void displayInfo(Scanner scanner, ArrayList<Person>people){
        System.out.println("Quan ly shop va school");
        System.out.println("Login");
        Person check = personService.login(scanner, people);

        if (check.getRole().equals("STAFF")){
            displayStaff();
        }else if (check.getRole().equals("CUSTOMER")){
            displayCustomer();
        }else if (check.getRole().equals("TEACHER")){
            displayTeacher();
        }else if (check.getRole().equals("STUDENT")){
            displayStudent();
        }

    }
    public void displayStaff(){
        System.out.println("===MENU STAFF===\n" +
                "1. Xem thông tin tất cả sp\n" +
                "2. xem thông tin tất cả đơn hàng\n" +
                "3. chỉnh sửa thông tin đơn hàng\n " +
                "4. xem thông tin khách hàng\n " +
                "5. chỉnh sửa thông tin khách hàng\n");
    }
    public void displayCustomer(){
        System.out.println("===MENU CUSTOMER===\n" +
                "1. Xem thông tin tất cả sp\n" +
                "2. xem thông tin tất cả đơn hàng cua ban than\n" +
                "3. Order sản phẩm\n " +
                "4. xem  thông tin cá nhân\n " +
                "5. chỉnh sửa  thông tin cá nhân\n");
    }
    public void displayTeacher(){
        System.out.println("===MENU CUSTOMER===\n" +
                "1. Xem thông tin các môn\n" +
                "2. xem thông tin tất cả HV mình dạy\n" +
                "3. chỉnh sửa điểm cho học viên\nn ");
    }
    public void displayStudent(){
        System.out.println("===MENU CUSTOMER===\n" +
                "1. xem thông tin các môn\n" +
                "2. xem thông tin các GV dạy mình\n");
    }
}
