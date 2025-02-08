package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String dateOfBirth; // Stored as a string in format yyyy-MM-dd
    private final String gender;
    private final String id;

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public Student(String firstName, String lastName, String dateOfBirth, String gender, String id) {
        if (firstName == null || firstName.trim().isEmpty())
            throw new IllegalArgumentException("First name cannot be empty.");

        if (lastName == null || lastName.trim().isEmpty())
            throw new IllegalArgumentException("Last name cannot be empty.");

        if (!dateOfBirth.matches("\\d{4}-\\d{2}-\\d{2}"))
            throw new IllegalArgumentException("Invalid format. Use yyyy-MM-dd.");

        int year = Integer.parseInt(dateOfBirth.substring(0, 4));
        int month = Integer.parseInt(dateOfBirth.substring(5, 7));
        int day = Integer.parseInt(dateOfBirth.substring(8, 10));

        if (year < 1900 || year > (2025))
            throw new IllegalArgumentException("Year of birth must be between 1900 and current year.");

        if (month < 1 || month > 12 || day < 1 || day > 31)
            throw new IllegalArgumentException("Invalid birth date.");

        gender = gender.toLowerCase();
        if (!(gender.equals("male") || gender.equals("female"))) {
            throw new IllegalArgumentException("Gender must be 'male', 'female'");
        }

        if (id == null || id.trim().isEmpty())
            throw new IllegalArgumentException("ID cannot be empty.");

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        int birthYear = Integer.parseInt(dateOfBirth.substring(0, 4));
        int birthMonth = Integer.parseInt(dateOfBirth.substring(5, 7));
        int birthDay = Integer.parseInt(dateOfBirth.substring(8, 10));

        int currentYear = 2025;
        int currentMonth = 1;
        int currentDay = 1;

        int age = currentYear - birthYear;

        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
            age--;
        }

        return age;
    }

    @Override
    public String toString() {
        return String.format("Student[ID=%s, Name=%s %s, DOB=%s, Gender=%s]", id, firstName, lastName,
                dateOfBirth, gender);
    }

}
