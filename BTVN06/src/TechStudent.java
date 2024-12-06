public abstract class TechStudent {
    private String name;
    private String study;

    public abstract double getScore();
    public String getLevel(){
        if (getScore()>=0 && getScore()<5){
            return "yeu";
        } else if (getScore() <=6.5) {
            return "trung binh";
        } else if (getScore()<7.5) {
            return "kha";
        }else {
            return "gioi";
        }
    };

    public void print(){
        System.out.println("Ten: "+name +"\tNganh: "+ study+ "\t Diem: " + getScore()+ "\tHoc luc: "+ getLevel());
    }

    @Override
    public String toString() {
        return "TechStudent{" +
                "Ten: "+name +"\tNganh: "
                + study+ "\t Diem: "
                + getScore()
                + "\tHoc luc: "
                + getLevel();
    }

    public TechStudent(String name, String study) {
        this.name = name;
        this.study = study;
    }
}
