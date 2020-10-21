import java.util.ArrayList;

public class Controller {
    static ArrayList<Employee> employeeArrayList = new ArrayList<>();
    static ArrayList<RentHistoryItem> rentHistory = new ArrayList<>();

    public static Employee registerEmployee(String name, String address, int birthyear, double salary) throws Exception {
        Employee employee = new Employee(name, address, birthyear, salary);

        Manager.registerEmployee(employee);
        return employee;
    }

    public static void removeEmployee(String ID) throws Exception {
        Employee removeEmployee = null;
        boolean removed = false;
        int i;
        for(i = 0; i < employeeArrayList.size(); i++){
            if(employeeArrayList.get(i).getID().equals(ID)){
                removeEmployee = employeeArrayList.get(i);
                removed = true;
                i = employeeArrayList.size();
            } else {
                System.out.println("Employee with id " + ID + " not found.");
            }
        }
        Manager.removeEmployee(removeEmployee);
        Screens.managerScreen();
    }
    public static String viewAllEmployee() throws Exception {
        String empStr = "";
        for (Employee manager : employeeArrayList) {
            empStr = empStr.concat(manager.toString() + System.lineSeparator());
        }
        return empStr;
    }

    public static double calcNetSalary(){

    }
}
