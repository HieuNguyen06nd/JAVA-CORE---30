package tiktok.entities;

public class Song {
    private int id;
    private String name;
    private String singer;

    public Song(int id, String name, String singer) {
        this.id = id;
        this.name = name;
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                '}';
    }
}
