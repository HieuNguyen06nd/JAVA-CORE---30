package tiktok.entities;

public class Follower extends Person {
    private int numberOfLike;

    public Follower(String name, int id, String email, int numberOfLike) {
        super(name, id, email);
        this.numberOfLike = numberOfLike;
    }
}
