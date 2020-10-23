public class Game extends Rentable {

    private String genre;

    public Game(String title, String genre, double dailyRent, int year) throws Exception {
        super(title, dailyRent, year);
        this.genre = genre;
    }

    @Override
    public String getGenre (){ return genre; }

    public String toString(){
        String s = getID() + " : " + this.title + " [" + getGenre() + "]. " + getDailyRent() + ". "
                + "Year: " + getYear()
                + " Rating: " + getAverageRating() + "/5"
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


