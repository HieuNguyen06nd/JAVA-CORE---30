package Lab04;

import Lab04.entities.DepartmentHead;
import Lab04.entities.MarketingStaff;
import Lab04.service.AdministrativeStaffService;
import Lab04.views.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<AdministrativeStaffService> administrativeStaffs = new ArrayList<>();

        ArrayList<DepartmentHead> departmentHeads = new ArrayList<>();

        ArrayList<MarketingStaff>marketingStaffs = new ArrayList<>();

        Menu menu = new Menu();

        while (true){
            menu.displayMenu(scanner);

        }

    }

}
