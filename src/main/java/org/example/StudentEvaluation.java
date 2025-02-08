package org.example;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentEvaluation {
    private static List<Student> students = new ArrayList<>();

    public static void addStudent(String firstName, String lastName, String dateOfBirth, String gender, String id) {
        Student student = new Student(firstName, lastName, dateOfBirth, gender, id);
        students.add(student);
    }

    public static void deleteStudent(String id) {
        if (id == null || id.trim().isEmpty())
            throw new IllegalArgumentException("ID cannot be empty.");

        Student student = students.stream().filter(s -> s.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Student with ID " + id + " does not exist."));

        students.remove(student);
    }

    @org.jetbrains.annotations.NotNull
    public static List<Student> retrieveStudentsByAge(int age) {
        if (age < 0)
            throw new IllegalArgumentException("Age cannot be negative.");

        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }

    @Contract("null -> fail")
    public static @NotNull List<Student> listStudents(String orderByName) {
        if (orderByName == null || orderByName.trim().isEmpty())
            throw new IllegalArgumentException("Order by field cannot be empty.");

        List<Student> sortedStudents = new ArrayList<>(students);
        orderByName = orderByName.toLowerCase();
        if (orderByName.equals("lastname")) {
            sortedStudents.sort(Comparator.comparing(Student::getLastName));
        } else if (orderByName.equals("birthdate")) {
            sortedStudents.sort(Comparator.comparing(Student::getDateOfBirth));
        } else {
            throw new IllegalArgumentException("Invalid order by field. Use 'LastName' or 'BirthDate'.");
        }
        return sortedStudents;
    }
}
