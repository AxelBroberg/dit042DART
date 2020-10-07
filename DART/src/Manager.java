
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

    private String ID;
    private int birthyear;
    private String address;
    private double grossSalary;
    private double netSalary;
    private String name;

    Manager(String name){ this.name = name; }

    public void setBirthyear(int birthyear){ this.birthyear = birthyear; }
    public int getBirthyear(){ return birthyear; }

    public void setAddress(String address){ this.address = address; }
    public String getAddress(){ return address; }

    public void setGrossSalary(double grossSalary){ this.grossSalary = grossSalary; }
    public double getGrossSalary(){ return grossSalary; }

    public void setNetSalary(double netSalary){ this.netSalary = netSalary; }
    public double getNetSalary(){ return netSalary; }

    public void setID(){ ID = Tools.randomizeID(); }
    public String getID(){ return ID; }





    public static void registerEmployee(Scanner input){

        System.out.print("Enter employee name: "); Manager emp = new Manager(input.nextLine());
        emp.setID(); System.out.println("Random ID <" + emp.getID() + "> was assigned.");
        System.out.print("Enter employee birth year: "); emp.setBirthyear(input.nextInt());
        input.nextLine();
        System.out.print("Enter employee address: "); emp.setAddress(input.nextLine());
        System.out.println("Enter employee gross salary: "); emp.setGrossSalary(input.nextDouble());

        System.out.print("You have added employee: ");
        emp.printEmployee();
        Controller.employeeArrayList.add(emp);
        Screens.managerScreen();
    }

    public static void viewAllEmployee(){
        Controller.employeeArrayList.forEach(Manager::printEmployee);
        Screens.managerScreen();
    }

    public static void removeEmployee(){
        Scanner input = new Scanner(System.in);
        boolean removed = false;
        int i;
        System.out.println("Employees: ");
        employeeArrayList.forEach(Manager::printEmployee);
        System.out.print("Enter the ID of employee you want to remove: ");

        String removeID = input.nextLine();
        for(i = 0; i < employeeArrayList.size(); i++){
            if(employeeArrayList.get(i).ID.equals(removeID)){
                employeeArrayList.remove(i);
                System.out.println("Successfully removed!");
                removed = true;
                i = employeeArrayList.size();
            }
        }
        if(!removed) System.out.println("Employee with id " + i + " not found.");
        Screens.managerScreen();
    }

    public void printEmployee(){
        System.out.print(getID() + " ");
        System.out.print(this.name +  " - " );
        System.out.print(getBirthyear() +  " ( ");
        System.out.print(Year.now().getValue()-getBirthyear() + " ): " );
        System.out.println("Gross salary: " + getGrossSalary() + " SEK");
        if(getNetSalary()!=0) System.out.println(" Net salary: " + getNetSalary() + " SEK");
    }

    public static void calcNetSalary(){
        final double GROSS_SALARY_TAX = 0.7;
        final int TAX_CONDITION = 100000;

        Scanner input = new Scanner(System.in);
        boolean found = false;
        int i;
        System.out.print("Enter ID of employee to calculate net salary: ");
        String ID = input.nextLine();

        for(i = 0; i < employeeArrayList.size(); i++) {
            if (employeeArrayList.get(i).ID.equals(ID)) {
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

        Scanner input = new Scanner(System.in);
        int bonus = 0;
        boolean found = false;
        int i;
        System.out.print("Enter ID of employee to see what bonus employee is eligible to: ");
        String ID = input.nextLine();

        for(i = 0; i < employeeArrayList.size(); i++) {
            if (employeeArrayList.get(i).ID.equals(ID)) {
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