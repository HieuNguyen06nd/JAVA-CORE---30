package tiktok.entities;

import java.util.ArrayList;
import java.util.List;

public class Idol  extends Person{
    private List<Follower> followers = new ArrayList<>();
    private String group;

    public Idol(String name, int id, String email, List<Follower> followers, String group) {
        super(name, id, email);
        this.followers = followers;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Idol{" +
                "name='" + super.getName() + '\'' +
                ", id=" + super.getId() +
                ", email='" + super.getEmail() + '\'' +
                ", followers=" + followers +
                ", group='" + group + '\'' +
                '}';
    }
}
