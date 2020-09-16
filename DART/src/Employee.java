import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

    int ID;
    int birthyear;
    String address;
    double grossSalary;
    double netSalary;
    String name;
    static ArrayList<Employee> employeeArrayList = new ArrayList();

    Employee(String name){ this.name = name; }

    public void setBirthyear(int birthyear){ this.birthyear = birthyear; }
    public int getBirthyear(){ return birthyear; }

    public void setAddress(String address){ this.address = address; }
    public String getAddress(){ return address; }

    public void setGrossSalary(double grossSalary){ this.grossSalary = grossSalary; }
    public double getGrossSalary(){ return grossSalary; }

    public void setNetSalary(double netSalary){ this.netSalary = netSalary; }
    public double getNetSalary(){ return netSalary; }

    public void setID(int ID){ this.ID = ID; }
    public int getID(){ return ID; }

    public static void registerEmployee(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter employee name: "); Employee emp = new Employee(input.nextLine());
        System.out.println("Enter employee ID: "); emp.setID(input.nextInt());
        System.out.println("Enter employee birth year: "); emp.setBirthyear(input.nextInt());
        input.nextLine();
        System.out.println("Enter employee address: "); emp.setAddress(input.nextLine());
        System.out.println("Enter employee gross salary: "); emp.setGrossSalary(input.nextDouble());

        System.out.print("You have added employee: ");
        emp.printEmployee();
        employeeArrayList.add(emp);
        Screens.managerScreen();
    }

    public static void viewAllEmployee(){
        employeeArrayList.forEach(Employee::printEmployee);
        Screens.managerScreen();
    }

    public static void removeEmployee(){
        Scanner input = new Scanner(System.in);
        boolean removed = false;
        int i;
        System.out.println("Employees: ");
        employeeArrayList.forEach(Employee::printEmployee);
        System.out.print("Enter the ID of employee you want to remove: ");

        int removeID = input.nextInt();
        for(i = 0; i < employeeArrayList.size(); i++){
            if(employeeArrayList.get(i).ID == removeID){
                employeeArrayList.remove(i);
                System.out.println("Successfully removed!");
                removed = true;
                i = employeeArrayList.size();
            }
        }
        if(!removed){
            System.out.println("Employee with id " + i + " not found.");
        }

    }

    public void printEmployee(){
        System.out.print(getID() + " ");
        System.out.print(this.name +  " - " );
        System.out.print(getBirthyear() +  " ( ");
        System.out.print(Year.now().getValue()-getBirthyear() + " ): " );
        System.out.println(getGrossSalary() + " SEK");
    }

    public static void calcNetSalary(){
        Scanner input = new Scanner(System.in);
        boolean found = false;
        int i;
        System.out.print("Enter ID of employee to calculate net salary: "); int ID = input.nextInt(); //FIX

        for(i = 0; i < employeeArrayList.size(); i++) {
            if (employeeArrayList.get(i).ID == ID) {
                if (employeeArrayList.get(i).getGrossSalary() >= 100000) {
                    employeeArrayList.get(i).setNetSalary(employeeArrayList.get(i).getGrossSalary() * 0.7);
                } else {
                    employeeArrayList.get(i).setNetSalary(employeeArrayList.get(i).getGrossSalary());
                }
                found = true;
                break;
            }
            if (!found) {
                System.out.println("Employee with id " + i + " not found.");
            }
        }

        System.out.println(employeeArrayList.get(i).getNetSalary());

        Screens.managerScreen();
    }

    /*
Employees that receive less than 100,000.00 SEK per year pay no taxes. Their net salary is their full gross salary
Employees that receive an amount greater than or equal to 100,000.00 SEK per year pay 30% of their gross salary as taxes.
     */

}