import java.sql.SQLOutput;

public class Employee {

    String employeeName;
    int birthYear;
    double employeeSalary;
    int employeeID;

    public Employee(String name){
        this.employeeName = name;
    }
    public String getName(){
        System.out.println(employeeName);
        return employeeName;
    }

    public void setEmployeeID(int id){
        employeeID = id;
    }
    public int getEmployeeID(){
        System.out.println(employeeID);
        return employeeID;
    }

    public void setBirthYear(int year){
        birthYear = year;
    }
    public int getBirthYear(){
        System.out.println(birthYear);
        return birthYear;
    }

    public void setMonthlySalary(double salary){
        employeeSalary = salary;
    }
    public double getMonthlySalary(){
        System.out.println(employeeSalary);
        return employeeSalary;
    }

    public void printEmployee(){
        getName();
        getEmployeeID();
        getBirthYear();
        getMonthlySalary();
    }



    // As a manager, I want to create employees in my system in order to handle customer operations.
    // An Employee has a unique employee id, a name, a birth year, an address and a monthly gross salary, all of them are needed to create an employee.
    // When printing an Employee, the following information is shows:
}
