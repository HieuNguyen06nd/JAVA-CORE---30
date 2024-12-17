package Lab05.view;

import Lab05.entities.Pet;
import Lab05.enums.TYPE;
import Lab05.service.PetService;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Menu {
    PetService petService = new PetService();
    public void displayMenu(Scanner scanner, ArrayList<Pet> pets){
        String choose;
        System.out.println("nhập thông tin pet của mình");
        Pet myPet = petService.inputPet(scanner);

        ArrayList<Pet> petArrayList = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getType() == myPet.getType() && pet.isSex() != myPet.isSex()) {
                petArrayList.add(pet);
//                System.out.println(pet);
            }
        }
        if (!petArrayList.isEmpty()){
            Random random = new Random();
            do {
                Pet randomPet= petArrayList.get(random.nextInt(petArrayList.size()));
                System.out.println(randomPet);

                System.out.println("Step3: “Bạn có muốn tìm chú pet khác k?(Y/N)");
                choose = scanner.nextLine();
            }while (choose.equalsIgnoreCase("Y"));
        }else {
            System.out.println("k co pet");
        }
    }

    public ArrayList<Pet> addPets(){
        ArrayList<Pet> pets = new ArrayList<>();
        pets.add(new Pet("Buddy1", "Buddy", 3, true, "Chó, tạo nên một phần tiến hóa của sói", TYPE.DOG, "buddy.jpg"));
        pets.add(new Pet("Chó bò5", "Chó bò", 3, false, "Chó, tạo nên một phần tiến hóa của sói", TYPE.DOG, "Chó bò.jpg"));
        pets.add(new Pet("Mèo Ba Tư4", "Mèo Ba Tư", 3, false, "Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt", TYPE.CAT, "Mèo Ba Tư.png"));
        pets.add(new Pet("Mèo Ba Tư5", "Mèo Ba Tư", 3, true, "Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt", TYPE.CAT, "Mèo Ba Tư.png"));
        pets.add(new Pet("Buddy2", "Buddy", 3, true, "Chó, tạo nên một phần tiến hóa của sói", TYPE.DOG, "buddy.jpg"));
        pets.add(new Pet("Chó bò1", "Chó bò", 3, false, "Chó, tạo nên một phần tiến hóa của sói", TYPE.DOG, "Chó bò.jpg"));
        pets.add(new Pet("Mèo Ba Tư", "Mèo Ba Tư", 3, false, "Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt", TYPE.CAT, "Mèo Ba Tư.png"));
        pets.add(new Pet("Mèo Ba Tư3", "Mèo Ba Tư", 3, true, "Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt", TYPE.CAT, "Mèo Ba Tư.png"));
        pets.add(new Pet("Buddy3", "Buddy", 3, true, "Chó, tạo nên một phần tiến hóa của sói", TYPE.DOG, "buddy.jpg"));
        pets.add(new Pet("Chó bò2", "Chó bò", 3, false, "Chó, tạo nên một phần tiến hóa của sói", TYPE.DOG, "Chó bò.jpg"));
        pets.add(new Pet("Mèo Ba Tư", "Mèo Ba Tư", 3, false, "Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt", TYPE.CAT, "Mèo Ba Tư.png"));
        pets.add(new Pet("Mèo Ba Tư2", "Mèo Ba Tư", 3, true, "Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt", TYPE.CAT, "Mèo Ba Tư.png"));
        pets.add(new Pet("Buddy4", "Buddy", 3, true, "Chó, tạo nên một phần tiến hóa của sói", TYPE.DOG, "buddy.jpg"));
        pets.add(new Pet("Chó bò3", "Chó bò", 3, false, "Chó, tạo nên một phần tiến hóa của sói", TYPE.DOG, "Chó bò.jpg"));
        pets.add(new Pet("Mèo Ba Tư1", "Mèo Ba Tư", 3, false, "Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt", TYPE.CAT, "Mèo Ba Tư.png"));
        pets.add(new Pet("Mèo Ba Tư", "Mèo Ba Tư", 3, true, "Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt", TYPE.CAT, "Mèo Ba Tư.png"));
        return pets;
    }
}
