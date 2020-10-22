import java.time.Year;
import java.util.ArrayList;

// We created this class because we got feedback on the last milestone that we are supposed to have a controller.
// We decided that we would have one controller per object class
// (customer, employee, game) instead of having one controller for the entire program
//
// In here we have the bonus, calcNetSalary, registerEmployee, removeEmployee & viewAllEmployee originating from the Employee class

//To implement epic feature 12, most methods now throw an exception to handle incorrect creation of employee
//registerEmployee() catches and handles any incorrect creation by printing the error to the user and letting them try again
//this repeats until an employee is created properly



public class Manager {



    public ArrayList<Employee> registerEmployee(ArrayList<Employee> employees, String name, String address, int bYear, double salary) {
        Employee employee = new Employee(name, address, bYear, salary);
        employees.add(employee);
        return employees;
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
        for (int i = 0; i < employeeArrayList.size(); i++)
            if (employeeArrayList.get(i).getID().equals(ID))
                return employeeArrayList.get(i);
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


    public Rentable mostProfitable() {
        Rentable mostProfit = null;
        if(Controller.gameList.size() > 0 && Controller.songList.size() > 0) {
            mostProfit = Controller.gameList.get(0);

            for (Rentable game : Controller.gameList) {
                if (game.getProfit() > mostProfit.getProfit()) {
                    mostProfit = game;
                }
            }

            for (Rentable song : Controller.songList) {
                if (song.getProfit() > mostProfit.getProfit()) {
                    mostProfit = song;
                }
            }
        }
        //If no songs
        else if(Controller.gameList.size() > 0){
            mostProfit = Controller.gameList.get(0);

            for (Rentable game : Controller.gameList) {
                if (game.getProfit() > mostProfit.getProfit()) {
                    mostProfit = game;
                }
            }
        }
        //If no games
        else if(Controller.songList.size() > 0){
            mostProfit = Controller.songList.get(0);
            for (Rentable song : Controller.songList) {
                if (song.getProfit() > mostProfit.getProfit()) {
                    mostProfit = song;
                }
            }
        }
        return mostProfit;
    }

    public Customer mostProfitableCustomer() {
        if (Controller.customerList.size() > 0) {
            Customer mostProfit = Controller.customerList.get(0);
            for (Customer customer : Controller.customerList)
                if (customer.getAmountSpent() > customer.getAmountSpent())
                    mostProfit = customer;

            return mostProfit;
        }
        return null;
    }
}
