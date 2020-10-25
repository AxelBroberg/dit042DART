import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

// ---------------------------------------Milestone 2-------------------------------------------
// We created this class because we got feedback on the last milestone that we are supposed to have a controller.
// We decided that we would have one controller per object class
// (customer, employee, game) instead of having one controller for the entire program
//
// In here we have the bonus, calcNetSalary, registerEmployee, removeEmployee & viewAllEmployee originating from the Employee class
// ---------------------------------------Milestone 3-------------------------------------------
// To implement epic feature 12, most methods now throw an exception to handle incorrect creation of employee
// registerEmployee() catches and handles any incorrect creation by printing the error to the user and letting them try again
// this repeats until an employee is created properly
// To implement Epic feature 13, we created the methods readFile & writeFile
// readFile is used to import data from text files
// writeFile is used to export data to a text file from the program


public class Manager {


    public void readFile(ArrayList<Employee> employees, ArrayList<Customer> customers, ArrayList<Rentable> items, Scanner input){
        try {
            File dartData = new File("dartData.txt");
            FileReader fr = new FileReader(dartData);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] dartInfo = line.split(";");
                switch (dartInfo[0]) {
                    case "Employee" -> registerEmployee(employees, dartInfo[1], dartInfo[2], Integer.parseInt(dartInfo[3]), Double.parseDouble(dartInfo[4]), input); //Employee;Name;Address;BirthYear;Salary
                    case "Game" -> items.add(new Game(dartInfo[1], dartInfo[2], Double.parseDouble(dartInfo[3]), Integer.parseInt(dartInfo[4]))); //Game;Title;Genre;RentPrice;ReleaseYear
                    case "Song" -> items.add(new Song(dartInfo[1], dartInfo[2], Double.parseDouble(dartInfo[3]), Integer.parseInt(dartInfo[4]))); //Song;Title;Artist;RentPrice;ReleaseYear
                    case "Customer" -> customers.add(new Customer(dartInfo[1], dartInfo[2])); //Customer;Name;Password
                }
            }
        }catch(Exception e){
            System.out.println("File does not exist");
        }
    }

    public void writeFile(ArrayList<RentHistoryItem> rentHistoryItems) throws IOException {
        BufferedWriter bfWriter = new BufferedWriter(new FileWriter(new File("rentTransactions.txt")));
        for (RentHistoryItem rentHistoryItem : rentHistoryItems)
            bfWriter.write(rentHistoryItem.toFileString() + "\n");

        bfWriter.close();
    }

    public void registerEmployee(ArrayList<Employee> employees, String name, String address, int bYear, double salary, Scanner input) {
        Employee employee = null;
        boolean inCorrect;

        do{
            try {
                employee = new Employee(name, address, bYear, salary);
                inCorrect = false;
            } catch (NameEmptyException e) {
                inCorrect = true;
                name = Tools.getString("Please input name again: ", input);
            } catch (NegativeSalaryException e){
                inCorrect = true;
                salary = Tools.getDouble("Please input salary again: ");
            }
        } while (inCorrect || employee == null);

        employees.add(employee);
    }

    public String viewAllEmployee(ArrayList<Employee> employeeArrayList) {
        String empStr = "";
        for (Employee manager : employeeArrayList) {
            empStr = empStr.concat(manager.toString() + System.lineSeparator());
        }
        return empStr;
    }

    public boolean removeEmployee(ArrayList<Employee> employeeArrayList, Employee removeEmployee) {
        return employeeArrayList.remove(removeEmployee);
    }

    public Employee findEmployee(ArrayList<Employee> employeeArrayList, String ID) {
        for (Employee employee : employeeArrayList)
            if (employee.getID().equals(ID))
                return employee;
        System.out.println("Employee with id " + ID + " not found.");
        return null;
    }

    public double calcNetSalary(Employee employee) {
        if (employee == null) return 0;
        final double GROSS_SALARY_TAX = 0.7;
        final int TAX_CONDITION = 100000;

        if (employee.getGrossSalary() >= TAX_CONDITION)
            employee.setNetSalary(employee.getGrossSalary() * GROSS_SALARY_TAX);
        else employee.setNetSalary(employee.getGrossSalary());

        return employee.getNetSalary();
    }

    public int bonus(Employee employee) {
        final int[] BONUS = new int[]{4000, 6000, 7500};
        final int[] YEAR_CONDITION = new int[]{22, 30};
        int bonus;

        if ((Year.now().getValue()-employee.getBirthyear()) < YEAR_CONDITION[0]) { bonus = BONUS[0]; }
        else if ((Year.now().getValue()-employee.getBirthyear()) < YEAR_CONDITION[1]) { bonus = BONUS[1]; }
        else bonus = BONUS[2];
        employee.setNetSalary(employee.getNetSalary() + bonus);

        return bonus;
    }

    public String viewRentFrequency(ArrayList<Rentable> itemsList) {
        String rentFreq = "";

        for (Rentable game : itemsList) {
            if(game.getRentFrequency() > 0 ){
                rentFreq = rentFreq.concat(game.getTitle() + " : " + game.getRentFrequency()) + System.lineSeparator();
            }
        }
        for (Rentable song : itemsList) {
            if(song.getRentFrequency() > 0 ){
                rentFreq = rentFreq.concat(song.getTitle() + " : " + song.getRentFrequency());
            }
        }
        return rentFreq;
    }


    public Rentable mostProfitable(ArrayList<Rentable> itemsList) {
        Rentable mostProfit = null;

        if(itemsList.size() > 0) {
            mostProfit = itemsList.get(0);
            for (Rentable rentable : itemsList) {
                if (rentable.getProfit() > mostProfit.getProfit()) {
                    mostProfit = rentable;
                }
            }
        }
        return mostProfit;
    }

    public Customer mostProfitableCustomer(ArrayList<Customer> customerList) {
        if (customerList.size() > 0) {
            Customer mostProfit = customerList.get(0);
            for (Customer customer : customerList)
                if (customer.getAmountSpent() > customer.getAmountSpent())
                    mostProfit = customer;
            return mostProfit;
        }
        return null;
    }
}
