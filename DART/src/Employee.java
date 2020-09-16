import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

    private static int count = 1;
    int employeeID;
    String employeeName;
    int birthYear;
    double employeeSalary;
    String employeeAddress;
    static ArrayList<Employee> employeeArrayList = new ArrayList<Employee>();

    public Employee(String name){
        this.employeeName = name;
    }
    public String getName(){ return employeeName; }

    public void setEmployeeID(){ employeeID=count++; }
    public int getEmployeeID(){ return employeeID; }

    public void setBirthYear(int year){
        birthYear = year;
    }
    public int getBirthYear(){ return birthYear; }

    public void setMonthlySalary(double salary){
        employeeSalary = salary;
    }
    public double getMonthlySalary(){ return employeeSalary; }

    public void setAddress(String address) { employeeAddress = address; }
    public String getEmployeeAddress(){ return employeeAddress; }

    public void printEmployee(){
        System.out.print(getEmployeeID() + " : ");
        System.out.print(getName() + " - ");
        System.out.print(getBirthYear() + " (");
        System.out.print(Year.now().getValue()-getBirthYear() + "): ");
        System.out.println(getMonthlySalary() + "SEK");
    }

    public static void registerEmployee(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter employee name: ");Employee emp = new Employee(input.nextLine()); emp.setEmployeeID();
        System.out.println("Employee will get ID: " + emp.getEmployeeID());
        System.out.print("Enter employee birth-year: "); emp.setBirthYear(input.nextInt());
        System.out.print("Enter employee monthly salary: "); emp.setMonthlySalary(input.nextDouble());
        System.out.println("Enter employee address: "); emp.setAddress(input.nextLine());

        employeeArrayList.add(emp);
        emp.printEmployee();
        Screens.managerScreen();
    }

    public static void viewAllEmployee(){
        employeeArrayList.forEach(Employee::printEmployee);
        Screens.managerScreen();
    }

    public static void removeEmployee(){

        Scanner input = new Scanner(System.in);
        System.out.println("Employees: ");
        employeeArrayList.forEach(Employee::printEmployee);
        System.out.print("Enter the ID of employee you want to remove:");

        String nEmployee;
        char[] employeeList = new char[employeeArrayList.size()];

        for (int i = 1; i < employeeArrayList.size(); i++) {
            employeeList[i] = (char) i;
        }

        employeeArrayList.removeIf(emp -> emp.employeeID == (int) DART.validateChar((char) input.nextInt(), String.valueOf(employeeList)));
    }

    // As a manager, I want to create employees in my system in order to handle customer operations.
    // An Employee has a unique employee id, a name, a birth year, an address and a monthly gross salary, all of them are needed to create an employee.
    // When printing an Employee, the following information is shows:
}
