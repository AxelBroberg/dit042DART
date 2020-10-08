public class Song extends Rentable{

    protected String artist;
    protected int year;
    protected double ratings;


    Song(String title, String artist, double dailyRent, int year, double ratings){
        super(title, dailyRent);
        this.artist = artist;
        this.year = year;
        this.ratings = ratings;
    }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getRatings() { return ratings; }
    public void setRatings(double ratings) { this.ratings = ratings; }

    public String toString(){
        String s = getID() + " : " + this.title + " - by " + getArtist() + ". Released in " + getYear() + ". Price: " + getDailyRent() + "SEK. " + "Status: ";

        if (getStatus()){
            s = s + "Available";
        }
        else{
            s = s + "Unavailable";
        }
        return s;
    }

}
