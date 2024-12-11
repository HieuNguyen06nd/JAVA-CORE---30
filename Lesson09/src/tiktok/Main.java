package tiktok;

import tiktok.entities.Follower;
import tiktok.entities.Idol;
import tiktok.entities.Song;
import tiktok.entities.Tiktok;
import tiktok.service.FollowerService;
import tiktok.service.IdolService;
import tiktok.service.SongService;
import tiktok.service.TiktokService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static  ArrayList<Follower>followers =new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FollowerService followerService = new FollowerService();
        IdolService idolService = new IdolService(followerService);
        SongService songService = new SongService();

        TiktokService tiktokService = new TiktokService(idolService, songService);

        ArrayList<Idol> idols = new ArrayList<>();
        ArrayList<Song> songs = new ArrayList<>();

        Tiktok tiktok = tiktokService.inputTikTok(scanner, idols, songs);

        System.out.println(tiktok);

    }



}
