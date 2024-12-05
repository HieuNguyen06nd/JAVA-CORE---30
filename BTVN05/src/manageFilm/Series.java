package manageFilm;

public class Series extends Film{
    private int episodeNumber;
    private int averageDuration;

    public Series() {
    }

    public Series(int id, String title, Category category, double ranking, int episodeNumber, int averageDuration) {
        super(id, title, category, ranking);
        this.episodeNumber = episodeNumber;
        this.averageDuration = averageDuration;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(int averageDuration) {
        this.averageDuration = averageDuration;
    }

    @Override
    public String toString() {
        return "Series{" +
                ", id=" + getId() +
                "title"+ super.getTitle()+
                ", rank "+ super.getRanking()+
                ", category=" + getCategory() +
                "episodeNumber=" + episodeNumber +
                ", averageDuration=" + averageDuration +
                '}';
    }
}
