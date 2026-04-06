package assignment2;

import java.util.Scanner;

class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void displaySalary(double before, double after) {
        System.out.println("\n--- Salary Details ---");
        System.out.println("Employee Name : " + name);
        System.out.printf("Salary Before : %.2f%n", before);
        System.out.printf("Salary After  : %.2f%n", after);
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    public double calculateSalary() {
        return salary + (0.50 * salary);
    }
}

class InternEmployee extends Employee {
    public InternEmployee(String name, double salary) {
        super(name, salary);
    }

    public double calculateSalary() {
        return salary + (0.25 * salary);
    }
}

public class EmployeeInheritanceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Employee Type (FullTime / Intern): ");
        String type = sc.nextLine();

        if (type.equalsIgnoreCase("FullTime")) {
            FullTimeEmployee emp = new FullTimeEmployee(name, salary);
            double after = emp.calculateSalary();
            emp.displaySalary(salary, after);

        } else if (type.equalsIgnoreCase("Intern")) {
            InternEmployee emp = new InternEmployee(name, salary);
            double after = emp.calculateSalary();
            emp.displaySalary(salary, after);

        } else {
            System.out.println("Invalid employee type.");
        }

        sc.close();
    }
}
