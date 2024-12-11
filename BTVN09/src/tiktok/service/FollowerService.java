package tiktok.service;

import tiktok.entities.Follower;

import java.util.Scanner;

public class FollowerService {
    public Follower inputFollower(Scanner scanner) {
        System.out.println("nhap ten follow");
        String name = scanner.nextLine();
        System.out.println("nhap id follow");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("nhap email follow");
        String email = scanner.nextLine();
        System.out.println("nhap so like follow");
        int numberOfLike = Integer.parseInt(scanner.nextLine());
        return new Follower(name, id, email, numberOfLike);
    }
}
