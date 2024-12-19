package service;

import entities.Category;

import java.util.Scanner;

public class CategoryService {
    public Category inputCate(Scanner scanner){
        System.out.println("nhap ten cate");
        String name = scanner.nextLine();

        return new Category(name);
    }
}
