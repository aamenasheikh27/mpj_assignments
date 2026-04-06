package assignment3;

import java.util.Scanner;

class Shapes {

    double side;
    double length, breadth;
    double base, height;

    Shapes() {
        this.side = 0;
    }

    Shapes(double side) {
        this.side = side;
    }

    Shapes(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    Shapes(double base, double height, boolean isTriangle) {
        this.base = base;
        this.height = height;
    }

    double area(double radius) {
        return Math.PI * radius * radius;
    }

    double area() {
        return side * side;
    }

    double area(double length, double breadth) {
        return length * breadth;
    }

    double area(double base, double height, boolean isTriangle) {
        return 0.5 * base * height;
    }
}

public class ShapesApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Area of Circle");
            System.out.println("2. Area of Square");
            System.out.println("3. Area of Rectangle");
            System.out.println("4. Area of Triangle");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter radius of circle: ");
                    double radius = sc.nextDouble();
                    Shapes c = new Shapes();
                    System.out.println("Area of Circle = " + String.format("%.2f", c.area(radius)));
                    break;

                case 2:
                    System.out.print("Enter side of square: ");
                    double side = sc.nextDouble();
                    Shapes s = new Shapes(side);
                    System.out.println("Area of Square = " + s.area());
                    break;

                case 3:
                    System.out.print("Enter length of rectangle: ");
                    double length = sc.nextDouble();
                    System.out.print("Enter breadth of rectangle: ");
                    double breadth = sc.nextDouble();
                    Shapes r = new Shapes(length, breadth);
                    System.out.println("Area of Rectangle = " + r.area(length, breadth));
                    break;

                case 4:
                    System.out.print("Enter base of triangle: ");
                    double base = sc.nextDouble();
                    System.out.print("Enter height of triangle: ");
                    double height = sc.nextDouble();
                    Shapes t = new Shapes(base, height, true);
                    System.out.println("Area of Triangle = " + t.area(base, height, true));
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }

        } while (choice != 5);

        sc.close();
    }
}