package ManageHouse.service;

import ManageHouse.entities.House;
import ManageHouse.entities.Menber;

import java.util.ArrayList;
import java.util.Scanner;

public class HouseService {

    private MemberService memberService;

    public HouseService(MemberService memberService) {
        this.memberService = memberService;
    }

    public House inputHouse(Scanner scanner, ArrayList<Menber> menbers){
            System.out.println("nhap so nha");
            int numberHome = Integer.parseInt(scanner.nextLine());
            System.out.println("nhap so member");
            int number = Integer.parseInt(scanner.nextLine());
            for (int j = 0; j < number; j++) {
                menbers.add(memberService.inputMember(scanner));
            }
            return new House(numberHome, menbers);

    }

    public void updateNumberHouse(Scanner scanner,ArrayList<House>houses){
        System.out.println("Nhap so nha cu");
        int numberHome = Integer.parseInt(scanner.nextLine());
        boolean check = false;
        for (House house: houses){
            if(house.getNumberHome()==numberHome){
                check=true;
                house.setNumberHome(numberHome);
                break;
            }
        }
        if (!check){
            System.out.println("khong ton tai so nha: "+ numberHome);
        }
    }
    public void deleteNumberHouse(Scanner scanner,ArrayList<House>houses){
        System.out.println("Nhap so nha can xoa");
        int numberHome = Integer.parseInt(scanner.nextLine());
        boolean check = false;
        for (House house: houses){
            if(house.getNumberHome()==numberHome){
                check=true;
                houses.remove(house);
                System.out.println("xoa thanh cong so nha: "+numberHome);
                break;
            }
        }
        if (!check){
            System.out.println("khong ton tai so nha: "+ numberHome);
        }
    }
}
