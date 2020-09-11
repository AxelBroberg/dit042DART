import java.io.*;

public class regEmployee {
    public static void main(String[] args){
        Employee empOne = new Employee("Axel Broberg");

        empOne.setBirthYear(28);
        empOne.setMonthlySalary(500);
        empOne.setEmployeeID(1);
        empOne.printEmployee();
    }
}
