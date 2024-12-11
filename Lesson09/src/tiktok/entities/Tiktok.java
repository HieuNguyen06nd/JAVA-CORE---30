package tiktok.entities;

import java.util.ArrayList;
import java.util.List;

public class Tiktok {
    private List<Idol> idols = new ArrayList<>();
    private List<Song> Songs = new ArrayList<>();

    public Tiktok(List<Idol> idols, List<Song> songs) {
        this.idols = idols;
        Songs = songs;
    }

    @Override
    public String toString() {
        return "Tiktok{" +
                "idols=" + idols +
                ", Songs=" + Songs +
                '}';
    }
}
