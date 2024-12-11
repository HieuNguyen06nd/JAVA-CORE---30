package tiktok.service;

import tiktok.entities.Song;

import java.util.Scanner;

public class SongService {
    public Song inputSong(Scanner scanner){
        System.out.println("nhap id");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("nhap ten bai hat");
        String name = scanner.nextLine();
        System.out.println("nhap ten ca si");
        String singer = scanner.nextLine();
        return new Song(id, name,singer);
    }
}
