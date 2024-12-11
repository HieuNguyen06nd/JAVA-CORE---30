package ManageHouse;

import ManageHouse.entities.House;
import ManageHouse.entities.Menber;
import ManageHouse.service.HouseService;
import ManageHouse.service.MemberService;
import techmaster.entities.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        MemberService memberService = new MemberService();
        HouseService houseService = new HouseService(memberService);
        ArrayList<House> houses = new ArrayList<>();
        ArrayList<Menber>menbers = new ArrayList<>();
        do {
            System.out.println("1- Hiển thị thông tin chi tiết \n" +
                    "2- Thực hiện thêm ho dan\n" +
                    "3- Thực hiện cập nhật thông tin so nha dựa vào Id\n" +
                    "4- Xoa ho dan\n");
            System.out.println("Mời bạn lựa chọn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose){
                case 1:
                    System.out.println(houses);
                    break;
                case 2:
                    House house = houseService.inputHouse(scanner,menbers);
                    houses.add(house);
                    System.out.println(houses);
                    break;
                case 3:
                    houseService.updateNumberHouse(scanner,houses);
                    break;
                case 4:
                    houseService.deleteNumberHouse(scanner,houses);
                    break;
                default:
                    System.exit(0);
            }
        } while (true);


    }
}
