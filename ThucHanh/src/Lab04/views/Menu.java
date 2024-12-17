package Lab04.views;

import Lab04.entities.AdministrativeStaff;
import Lab04.entities.DepartmentHead;
import Lab04.entities.MarketingStaff;
import Lab04.entities.Person;
import Lab04.service.AdministrativeStaffService;
import Lab04.service.DepartmentHeadService;
import Lab04.service.MarketingStaffService;
import Lab04.service.PersonService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    MarketingStaffService marketingStaffService = new MarketingStaffService();
    ArrayList<MarketingStaff> marketingStaffs= new ArrayList<>();

    DepartmentHeadService departmentHeadService = new DepartmentHeadService();
    ArrayList<DepartmentHead> departmentHeads = new ArrayList<>();

    AdministrativeStaffService staffService = new AdministrativeStaffService();
    ArrayList<AdministrativeStaff>administrativeStaffs = new ArrayList<>();

    ArrayList<Person> people = new ArrayList<>();

    PersonService personService = new PersonService();


    public void displayMenu(Scanner scanner){
        System.out.println("1. Nhập nha vien maketing \n" +
                "2. nhap nhan vi hanh chinh\n" +
                "3. nhap nhan vien truong phong\n "+
                "4. xuất danh sách nhân viên\n" +
                "5. Xóa nhân viên id\n" +
                "6. cập nhật thông tin nhân viên\n" +
                "7. Tìm kiếm nhân viên theo lương\n" +
                "8. Sắp xếp nhân viên theo họ tên\n" +
                "9. Xuất 5 nhân viên có thu nhập cao nhất công ty\n");
        System.out.println("nhap lua chon");
        selectMenu(scanner);
    }

    public void selectMenu(Scanner scanner){
        int choose =Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                MarketingStaff marketingStaff = marketingStaffService.inputMarketing(scanner);
                people.add(marketingStaff);
                break;
            case 2:
                AdministrativeStaff administrativeStaff = (AdministrativeStaff) staffService.inputAdministrative(scanner);
                people.add(administrativeStaff);
                break;
            case 3:
                DepartmentHead departmentHead = departmentHeadService.inputDepartmentHead(scanner);
                people.add(departmentHead);
                break;
            case 4:
                System.out.println(people);
                break;
            case 5:
                personService.deleteById(scanner,people);
                break;
            case 6:
                personService.updateById(scanner,people);
                break;
            case 7:
                personService.findBySalary(scanner,people);
                break;
            case 8:
                personService.sortName(people);
                System.out.println(people);
                break;
            case 9:
                personService.top5(people);
                break;
            default:
                System.exit(1);
        }
    }
}
