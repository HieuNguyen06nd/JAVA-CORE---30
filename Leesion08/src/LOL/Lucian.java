package LOL;

public class Lucian implements ISurf,IShot {
    private String feature;
    private String weapon;


    @Override
    public void shot() {
        System.out.println("ban");
    }

    @Override
    public void surf() {
        System.out.println("luot");
    }

    public Lucian(String feature, String weapon) {
        this.feature = feature;
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Lucian{" +
                "feature='" + feature + '\'' +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
