import java.time.Year;
import java.util.ArrayList;

// We created this class because we got feedback on the last milestone that we are supposed to have a controller.
// We decided that we would have one controller per object class
// (customer, employee, game) instead of having one controller for the entire program
//
// In here we have the bonus, calcNetSalary, registerEmployee, removeEmployee & viewAllEmployee originating from the Employee class

//To implement epic feature 12, most methods now throw an exception to handle incorrect creation of employee
//registerEmployee() catches and handles any incorrect creation by printing the error to the user and letting them try again
//this repeats until an employee is created properly



public class Manager {

    public static void registerEmployee(Employee employee) throws Exception {
        Controller.employeeArrayList.add(employee);
    }

    public static void removeEmployee(Employee removeEmployee) throws Exception {
        Controller.employeeArrayList.remove(removeEmployee);
    }

    public static void calcNetSalary() throws Exception {
        final double GROSS_SALARY_TAX = 0.7;
        final int TAX_CONDITION = 100000;
        boolean found = false;
        int i;
        String ID = Tools.getString("Enter ID of employee to calculate net salary: ");

        for(i = 0; i < Controller.employeeArrayList.size(); i++) {
            if (Controller.employeeArrayList.get(i).getID().equals(ID)) {
                if (Controller.employeeArrayList.get(i).getGrossSalary() >= TAX_CONDITION) {
                    Controller.employeeArrayList.get(i).setNetSalary(Controller.employeeArrayList.get(i).getGrossSalary() * GROSS_SALARY_TAX);
                } else {
                    Controller.employeeArrayList.get(i).setNetSalary(Controller.employeeArrayList.get(i).getGrossSalary());
                }
                found = true;
                break;
            }
        }
        if (!found) { System.out.println("Employee with id " + i + " not found."); }
        System.out.println(Controller.employeeArrayList.get(i).getNetSalary());
        Screens.managerScreen();
    }

    public static void bonus() throws Exception {
        final int[] BONUS = new int[]{4000, 6000, 7500};
        final int[] YEAR_CONDITION = new int[]{22, 30};

        int bonus = 0;
        boolean found = false;
        int i;
        String ID = Tools.getString("Enter ID of employee to see what bonus employee is eligible to: ");

        for(i = 0; i < employeeArrayList.size(); i++) {
            if (employeeArrayList.get(i).getID().equals(ID)) {
                if ((Year.now().getValue()-employeeArrayList.get(i).getBirthyear()) < YEAR_CONDITION[0]) { bonus = BONUS[0]; }
                else if ((Year.now().getValue()-employeeArrayList.get(i).getBirthyear()) < YEAR_CONDITION[1]) { bonus = BONUS[1]; }
                else bonus = BONUS[2];
                employeeArrayList.get(i).setNetSalary(employeeArrayList.get(i).getNetSalary() + bonus);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Employee with id " + i + " not found.");
        System.out.println("Employee bonus is: " + bonus);
        Screens.managerScreen();
    }


    public static void mostProfitable() throws Exception {
        if(GameController.gameList.size() > 0 && SongController.songList.size() > 0) {
            Rentable mostProfit = GameController.gameList.get(0);

            for (Rentable game : GameController.gameList) {
                if (game.getProfit() > mostProfit.getProfit()) {
                    mostProfit = game;
                }
            }

            for (Rentable song : SongController.songList) {
                if (song.getProfit() > mostProfit.getProfit()) {
                    mostProfit = song;
                }
            }

            System.out.println("Most profitable item: " + mostProfit.toString());

        }
        //If no songs
        else if(GameController.gameList.size() > 0){
            Rentable mostProfit = GameController.gameList.get(0);

            for (Rentable game : GameController.gameList) {
                if (game.getProfit() > mostProfit.getProfit()) {
                    mostProfit = game;
                }
            }
            System.out.println("Most profitable item: " + mostProfit.toString());
        }
        //If no games
        else if(SongController.songList.size() > 0){
            Rentable mostProfit = SongController.songList.get(0);
            for (Rentable song : SongController.songList) {
                if (song.getProfit() > mostProfit.getProfit()) {
                    mostProfit = song;
                }
            }
            System.out.println("Most profitable item: " + mostProfit.toString());
        }

        else {
            System.out.println("There are no games or songs");
        }
        Screens.managerScreen();
    }


    public static void viewRentFrequency() throws Exception {
        for (Rentable game : GameController.gameList) {
            if(game.getRentFrequency() > 0 ){
                System.out.println(game.getTitle() + " : " + game.getRentFrequency());
            }
        }
        for (Rentable song : SongController.songList) {
            if(song.getRentFrequency() > 0 ){
                System.out.println(song.getTitle() + " : " + song.getRentFrequency());
            }
        }
        Screens.managerScreen();
    }

    public static void mostProfitableCustomer() throws Exception {
        if (EmployeeController.customerList.size() > 0) {
            Customer mostProfit = EmployeeController.customerList.get(0);

            for (Customer customer : EmployeeController.customerList) {
                if (customer.getAmountSpent() > customer.getAmountSpent()) {
                    mostProfit = customer;
                }
            }
            System.out.println("Most profitable customer: " + mostProfit.toString());
        }
        Screens.managerScreen();
    }


}
