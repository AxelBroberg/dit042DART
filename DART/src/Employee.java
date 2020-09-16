import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

    int ID;
    int birthyear;
    String address;
    double grossSalary;
    String name;
    static ArrayList<Employee> employeeArrayList = new ArrayList();

    Employee(String name){ this.name = name; }

    public void setBirthyear(int birthyear){ this.birthyear = birthyear; }
    public int getBirthyear(){ return birthyear; }

    public void setAddress(String address){ this.address = address; }
    public String getAddress(){ return address; }

    public void setGrossSalary(double grossSalary){ this.grossSalary = grossSalary; }
    public double getGrossSalary(){ return grossSalary; }

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
        System.out.println("Employees: ");
        employeeArrayList.forEach(Employee::printEmployee);
        System.out.print("Enter the ID of employee you want to remove: ");

        char[] employeeAmount = new char[employeeArrayList.size()];

        for(int i = 0; i < employeeArrayList.size(); i++){
            employeeAmount[i] = (char) i;
        }

        String amount = String.valueOf(employeeAmount);


            DART.validateChar(input.nextLine().charAt(0), amount)
        });




        //Remove  Employees  based on their IDs  / If the specified ID is not found, the system should print the message: “Employee with id <ID> not found”.
    }

    public void printEmployee(){
        System.out.print(getID() + " ");
        System.out.print(this.name +  " - " );
        System.out.print(getBirthyear() +  " ( ");
        System.out.print(Year.now().getValue()-getBirthyear() + " ): " );
        System.out.println(getGrossSalary() + " SEK");
    }

}