import java.time.Year;
import java.util.ArrayList;

public class ManagerController {
    static ArrayList<Manager> employeeArrayList = new ArrayList();

    public static void registerEmployee() {
        Manager emp = new Manager(
            Tools.getString("Enter employee name: "),
            Tools.getString("Enter employee address: "),
            Tools.getInt("Enter employee birth year: "),
            Tools.getDouble("Enter employee gross salary: "));
        System.out.println("Random ID <" + emp.getID() + "> was assigned.");

        System.out.print("You have added employee: ");
        emp.toString();
        employeeArrayList.add(emp);
        Screens.managerScreen();
    }

    public static void viewAllEmployee(){
        employeeArrayList.forEach(Manager::toString);
        Screens.managerScreen();
    }

    public static void removeEmployee(){
        boolean removed = false;
        int i;
        System.out.println("Employees: ");
        viewAllEmployee();
        String removeID = Tools.getString("Enter the ID of employee you want to remove: ");
        for(i = 0; i < employeeArrayList.size(); i++){
            if(employeeArrayList.get(i).getID().equals(removeID)){
                employeeArrayList.remove(i);
                System.out.println("Successfully removed!");
                removed = true;
                i = employeeArrayList.size();
            }
        }
        if(!removed) System.out.println("Employee with id " + i + " not found.");
        Screens.managerScreen();
    }

    /*public void printEmployee(){
        System.out.print(getID() + " ");
        System.out.print(this.name +  " - " );
        System.out.print(getBirthyear() +  " ( ");
        System.out.print(Year.now().getValue()-getBirthyear() + " ): " );
        System.out.println("Gross salary: " + getGrossSalary() + " SEK");
        if(getNetSalary()!=0) System.out.println(" Net salary: " + getNetSalary() + " SEK");
    }*/

    public static void calcNetSalary(){
        final double GROSS_SALARY_TAX = 0.7;
        final int TAX_CONDITION = 100000;
        boolean found = false;
        int i;
        String ID = Tools.getString("Enter ID of employee to calculate net salary: ");

        for(i = 0; i < employeeArrayList.size(); i++) {
            if (employeeArrayList.get(i).getID().equals(ID)) {
                if (employeeArrayList.get(i).getGrossSalary() >= TAX_CONDITION) {
                    employeeArrayList.get(i).setNetSalary(employeeArrayList.get(i).getGrossSalary() * GROSS_SALARY_TAX);
                } else {
                    employeeArrayList.get(i).setNetSalary(employeeArrayList.get(i).getGrossSalary());
                }
                found = true;
                break;
            }
        }
        if (!found) { System.out.println("Employee with id " + i + " not found."); }
        System.out.println(employeeArrayList.get(i).getNetSalary());
        Screens.managerScreen();
    }

    public static void bonus(){
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
}
