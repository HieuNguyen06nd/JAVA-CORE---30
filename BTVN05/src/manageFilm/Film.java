package manageFilm;

public class Film {
    private int id;
    private String title;
    private Category category;
    private double ranking;

    public Film() {
    }

    public Film(int id, String title, Category category, double ranking) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.ranking = ranking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getRanking() {
        return ranking;
    }

    public void setRanking(double ranking) {
        this.ranking = ranking;
    }
}
