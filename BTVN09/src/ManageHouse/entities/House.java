package ManageHouse.entities;

import java.util.ArrayList;
import java.util.List;

public class House {
    private int numberHome;
    private List<Menber> menbers = new ArrayList<>();

    public House(int numberHome, List<Menber> menbers) {
        this.numberHome = numberHome;
        this.menbers = (List<Menber>) menbers;
    }

    @Override
    public String toString() {
        return "House{" +
                "numberHome=" + numberHome +
                ", menbers=" + menbers +
                '}';
    }

    public int getNumberHome() {
        return numberHome;
    }

    public void setNumberHome(int numberHome) {
        this.numberHome = numberHome;
    }

    public List<Menber> getMenbers() {
        return menbers;
    }

    public void setMenbers(List<Menber> menbers) {
        this.menbers = menbers;
    }
}
