package tiktok.service;

import tiktok.entities.Idol;
import tiktok.entities.Song;
import tiktok.entities.Tiktok;

import java.util.ArrayList;
import java.util.Scanner;

public class TiktokService {
    private IdolService idolService;
    private SongService songService;

    public TiktokService(IdolService idolService, SongService songService) {
        this.idolService = idolService;
        this.songService = songService;
    }

    public Tiktok inputTikTok (Scanner scanner, ArrayList<Idol>idols, ArrayList<Song>songs){
        System.out.println("Nhap so idol");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            idols.add(idolService.inputIdol(scanner,new ArrayList<>()));
        }
        System.out.println("nhap so bai hat");
        n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            songs.add(songService.inputSong(scanner));
        }

        return new Tiktok(idols,songs);
    }

}
