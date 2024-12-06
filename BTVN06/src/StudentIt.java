public class StudentIt extends TechStudent{
    private double java, html , css;


    @Override
    public double getScore() {
        return (2*java + html + css)/4;
    }

    public StudentIt(String name, String study, double java, double html, double css) {
        super(name, study);
        this.java = java;
        this.html = html;
        this.css = css;
    }
}
