public class Album {
    public static final int MAX_STRING_FIELD_LENGTH = 30;
    public static final int
            MAX_RECORD_LENGTH = 2 * (MAX_STRING_FIELD_LENGTH * Character.BYTES) + 2 * Integer.BYTES + Float.BYTES;
    private String title;
    private String artist;
    private int year;
    private int numberOfTracks;
    private float cost;

    public Album(String title, String artist, int year, int numberOfTracks, float cost) {
        this.title = "";
        this.artist = "";
        setTitle(title);
        setArtist(artist);
        setYear(year);
        setNumberOfTracks(numberOfTracks);
        setCost(cost);
    }

    public Album() {
        this("", "", 0, 0, 0.0F);
    }

    public void setTitle(String title) {
        if (title.length() <= MAX_STRING_FIELD_LENGTH)
            this.title = title;
    }

    public void setArtist(String artist) {
        if (artist.length() <= MAX_STRING_FIELD_LENGTH)
            this.artist = artist;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setNumberOfTracks(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", year=" + year +
                ", numberOfTracks=" + numberOfTracks +
                ", cost=" + cost +
                '}';
    }
}