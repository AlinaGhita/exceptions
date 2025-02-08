package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            // Adding students
            StudentEvaluation.addStudent("Alina", "Ghita", "2000-05-15", "Male", "576879");
            StudentEvaluation.addStudent("Ilie", "Pops", "1678-10-25", "Female", "789012");

            // List students by last name
            System.out.println("Students sorted by Last Name:");
            StudentEvaluation.listStudents("LastName").forEach(System.out::println);

            // Retrieve students by age
            System.out.println("\nStudents aged 24:");
            StudentEvaluation.retrieveStudentsByAge(24).forEach(System.out::println);

            // Delete a student
            StudentEvaluation.deleteStudent("576879");
            System.out.println("\nStudents after deleting ID 576879:");
            StudentEvaluation.listStudents("LastName").forEach(System.out::println);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
