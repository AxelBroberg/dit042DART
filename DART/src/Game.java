public class Game extends Rentable{

    private String genre;

    Game(String title, String genre, double dailyRent){
        super(title, dailyRent);
        this.genre = genre;
    }

    public void setGenre (String genre){ this.genre = genre; }
    public String getGenre (){ return genre; }

    public String toString(){
        String s = getID() + " : " + this.title + " [" + getGenre() + "]. " + getDailyRent() + ". " + "Status: ";

        if (getStatus()){
            s = s + "Available";
        }
        else{
            s = s + "Unavailable";
        }
        return s;
    }


}
