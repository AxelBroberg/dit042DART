/*
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Customer {
    int id;
    String name;

    public Customer(int id, String name){
        this.id = id;
        this.name = name;
    }
}

public class Customers {

    public static void main(String[] args) throws IOException {
        //FileWriter writer = new FileWriter("./customerDB.txt");
        File file = new File("./customerDB.txt");
        FileWriter writer = new FileWriter(file);

        registerCustomer(writer);
        registerCustomer(writer);
        writer.close();
        showAllCustomers(file);

        //deleteCustomer(file);

        showAllCustomers(file);

    }


    public static void registerCustomer(FileWriter writer) throws IOException {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String name = input.nextLine();

        Customer customer = new Customer(rand.nextInt(1000), name);

        writer.write(customer.id + " : " + customer.name + "\n");
    }


    public static void deleteCustomer(File file) throws IOException {  //NOT WORKING PROPERLY
        Scanner scan = new Scanner(file);
        String customerDBContent = "";
        boolean replacedCustomer = false;

        Scanner input = new Scanner(System.in);
        System.out.println("\nDELETE CUSTOMER");
        System.out.print("Enter customer ID: ");
        String customerID = input.nextLine();

        while(scan.hasNextLine()) {
            if(!(scan.nextLine().startsWith(customerID))){
                customerDBContent = customerDBContent.concat(scan.nextLine() + "\n");
            } else {
                scan.nextLine();
                replacedCustomer = true;
            }
        }

        if (replacedCustomer) {
            FileWriter writer = new FileWriter("./customerDB.txt");
            writer.write(customerDBContent);
            writer.close();
            System.out.println("Customer deleted");
        } else {
            System.out.println("Customer not found");
        }

    }


    public static void showAllCustomers(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
    }


}





/*

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Customer {
    int id;
    String name;
}

public class Customers {

    public static void main(String[] args){
        Customer[] listOfCustomers;
        listOfCustomers = new Customer[2];

        listOfCustomers[0] = registerCustomer();
        listOfCustomers[1] = registerCustomer();

        showAllCustomers(listOfCustomers);

        deleteCustomer(listOfCustomers);

        showAllCustomers(listOfCustomers);

    }


    public static Customer registerCustomer() {
        Random rand = new Random();
        Customer customer = new Customer();
        Scanner input = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String name = input.nextLine();

        customer.name = name;
        customer.id = rand.nextInt(1000);


        return customer;
    }

    public static void deleteCustomer(Customer[] customers) {
        boolean replacedCustomer = false;
        Customer emptyCustomer = new Customer();
        emptyCustomer.name = "";
        emptyCustomer.id = -1;
        Scanner input = new Scanner(System.in);

        System.out.println("\nDELETE CUSTOMER");
        System.out.print("Enter customer ID: ");
        int customerID = input.nextInt();

        for(int i = 0; i < customers.length; i++) {
            if (customers[i].id == customerID) {
                customers[i] = emptyCustomer;
                replacedCustomer = true;
            }
        }

        if (replacedCustomer) {
            System.out.println("Customer deleted");
        }
        else {
            System.out.println("Customer not found");
        }
    }

    public static void showAllCustomers(Customer[] customers){
        for(Customer customer : customers){
            System.out.println(customer.id + " : " + customer.name );
        }
    }


}
 */
