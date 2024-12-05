package manageFilm;

public class Movie extends Film{
    private int time;

    public Movie() {
    }

    public Movie(int id, String title, Category category, double ranking, int time) {
        super(id, title, category, ranking);
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Movie{" +
                " id=" + getId() +
                "title"+ super.getTitle()+
                ", category=" + getCategory() +
                ", rank "+ super.getRanking()+
                ", time=" + time +
                '}';
    }
}
