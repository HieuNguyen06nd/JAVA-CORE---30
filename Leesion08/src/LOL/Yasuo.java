package LOL;

public class Yasuo implements ISurf, IDash {
    private String feature;
    private String weapon;

    @Override
    public void dash() {
        System.out.println("chem");
    }

    @Override
    public void surf() {
        System.out.println("luot");
    }

    public Yasuo(String feature, String weapon) {
        this.feature = feature;
        this.weapon = weapon;
    }
}
