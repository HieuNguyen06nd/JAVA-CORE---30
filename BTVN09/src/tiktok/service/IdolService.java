package tiktok.service;

import tiktok.entities.Follower;
import tiktok.entities.Idol;

import java.util.ArrayList;
import java.util.Scanner;

public class IdolService {
    private FollowerService followerService;

    public IdolService(FollowerService followerService) {
        this.followerService = followerService;
    }

    public Idol inputIdol (Scanner scanner, ArrayList<Follower>followers){
        System.out.println("ten idol");
        String name = scanner.nextLine();
        System.out.println("nhap id ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("nhap email idol");
        String email = scanner.nextLine();
        System.out.println("Nhap so follower");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            followers.add(followerService.inputFollower(scanner));
        }
        System.out.println("nhap group");
        String group = scanner.nextLine();
        return new Idol(name, id, email,followers,group);
    }
}
