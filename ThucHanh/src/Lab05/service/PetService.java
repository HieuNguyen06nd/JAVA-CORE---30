package Lab05.service;

import Lab05.entities.Pet;
import Lab05.enums.TYPE;

import java.util.Scanner;

public class PetService {
    public Pet inputPet(Scanner scanner){
        System.out.println("nhap ten");
        String name = scanner.nextLine();

        System.out.println("nhap species");
        String species = scanner.nextLine();

        System.out.println("nhap age");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("nhap sex(neu la duc chon: 1 - cai chon : 0");
        int sexBoolean = Integer.parseInt(scanner.nextLine());
        boolean sex = (sexBoolean == 1);

        System.out.println("nhap description");
        String description = scanner.nextLine();

        System.out.println("nhap TYPE(cho chon: 1- meo chon: 0");
        int typeChoice = Integer.parseInt(scanner.nextLine());
        TYPE type= (typeChoice ==1)?TYPE.DOG: TYPE.CAT;

        System.out.println("nhap images ");
        String images = scanner.nextLine();

        return new Pet(name,species,age,sex,description,type,images);
    }
}
