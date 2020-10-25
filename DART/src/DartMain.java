public class DartMain {

// ---------------------------------------Milestone 2-------------------------------------------
// The main has been cleared up since the last milestone, we will however make the main only a few lines whenever we get around to it
// ---------------------------------------Milestone 3-------------------------------------------
// The main is now only a few lines long

    static boolean programRunning = true;
    public static void main(String[] args) throws Exception {
        Controller main = new Controller();
        while (programRunning) {
            main.mainMenu();
        }
    }
}