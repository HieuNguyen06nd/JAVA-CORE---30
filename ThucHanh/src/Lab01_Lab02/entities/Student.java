package Lab01_Lab02.entities;

public class Student {
    private static int autiId;

    private int id;

    private String name;
    private String address;

    private double scoreMath;
    private double scorePhysic;


    public Student( String name, String address, double scoreMath, double scorePhysic) {
        this.id = ++autiId;
        this.name = name;
        this.address = address;
        this.scoreMath = scoreMath;
        this.scorePhysic =scorePhysic;
    }
    public String checkScore(){
        if ((this.scoreMath + this.scorePhysic)/2 >0 && (this.scoreMath + this.scorePhysic)/2<6.5){
           return "C";
        } else if ((this.scoreMath + this.scorePhysic)< 8 ) {
            return "B";
        }else {
            return "A";
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", scoreMath=" + scoreMath +
                ", xep loai=" + checkScore()+
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getScoreMath() {
        return scoreMath;
    }

    public void setScoreMath(Double scoreMath) {
        this.scoreMath = scoreMath;
    }
}
