public class Song extends Rentable{

    // In order to implement epic feature 7 we created a new class Song, which extends Rentable

    protected String artist;
    protected int year;



    Song(String title, String artist, double dailyRent, int year){
        super(title, dailyRent);
        this.artist = artist;
        this.year = year;
    }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    @Override
    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }



    public String toString(){
        String s = getID() + " : " + this.title + " - by " + getArtist() + ". Released in " + getYear() + ". Price: " + getDailyRent() + "SEK. "
                + "Rating: " + getAverageRating() + "/5"
                + " Status: ";

        if (getStatus()){
            s = s + "Available";
        }
        else{
            s = s + "Unavailable";
        }
        return s;
    }

}
