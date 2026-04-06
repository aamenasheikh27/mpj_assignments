package assignment1;

import java.util.Scanner;

public class SubjectAverageApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int students = sc.nextInt();

        int subjects = 5;
        int[][] marks = new int[students][subjects];

        for (int i = 0; i < students; i++) {
            System.out.println("Enter marks for Student " + (i + 1));
            for (int j = 0; j < subjects; j++) {
                System.out.print("Subject " + (j + 1) + ": ");
                marks[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nSubject-wise Average and Grade:");
        for (int j = 0; j < subjects; j++) {
            int sum = 0;
            for (int i = 0; i < students; i++) {
                sum += marks[i][j];
            }

            double average = (double) sum / students;
            char grade;

            if (average >= 75)
                grade = 'A';
            else if (average >= 60)
                grade = 'B';
            else if (average >= 50)
                grade = 'C';
            else
                grade = 'D';

            System.out.printf(
                    "Subject %d -> Average: %.2f, Grade: %c%n",
                    (j + 1), average, grade
            );
        }

        sc.close();
    }
}
