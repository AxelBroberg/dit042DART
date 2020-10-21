import java.util.ArrayList;

public class Controller {
    static ArrayList<Employee> employeeArrayList = new ArrayList<>();
    static ArrayList<RentHistoryItem> rentHistory = new ArrayList<>();
    static ArrayList<Customer> customerList = new ArrayList<>();//creates ArrayList named 'customerList' containing Customers
    static ArrayList<String> upgradeRequestsID = new ArrayList<>();


    public static Employee registerEmployee(String name, String address, int birthyear, double salary) throws Exception {
        Employee employee = new Employee(name, address, birthyear, salary);
        Manager.registerEmployee(employee);
        return employee;
    }

    public static void removeEmployee(String ID) {
        Manager.removeEmployee(findEmployee(ID));
    }

    public static String viewAllEmployee() {
        String empStr = "";
        for (Employee manager : employeeArrayList) {
            empStr = empStr.concat(manager.toString() + System.lineSeparator());
        }
        return empStr;
    }

    public static double calcNetSalary(String ID) {
        double netSalary = Manager.calcNetSalary(findEmployee(ID));
        return netSalary;
    }

    public static int bonus(String ID) {
        return Manager.bonus(findEmployee(ID));
    }

    public static Rentable mostProfitable() {
        return Manager.mostProfitable();
    }

    public static String viewRentFrequency() {
        String rentFreq = "";
        for (Rentable game : GameController.gameList) {
            if(game.getRentFrequency() > 0 ){
                rentFreq = rentFreq.concat(game.getTitle() + " : " + game.getRentFrequency()) + System.lineSeparator();
            }
        }
        for (Rentable song : SongController.songList) {
            if(song.getRentFrequency() > 0 ){
                rentFreq = rentFreq.concat(song.getTitle() + " : " + song.getRentFrequency());
            }
        }
        return rentFreq;
    }

    public static Customer mostProfitableCustomer() {
        return Manager.mostProfitableCustomer();
    }

    public static Employee findEmployee(String ID) {
        for (int i = 0; i < Controller.employeeArrayList.size(); i++)
            if (Controller.employeeArrayList.get(i).getID().equals(ID))
                return Controller.employeeArrayList.get(i);
        System.out.println("Employee with id " + ID + " not found.");
        return null;
    }

    public static void showItems(){
        Game.customerViewAllGames();
        Song.customerViewAllSongs();
    }

    public static Customer registerCustomer(String name) throws Exception{
        Customer customer = new Customer(name);
        Employee.registerCustomer(customer);
    }
}
