package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private Student student;

    @BeforeEach
    void setUpStudent() {

        student = new Student("Alina", "Ghita", "2000-05-15", "Male", "576879");
    }
    @Test
    void testValidStudentCreation() {
        assertNotNull(student);
        assertEquals("Alina", student.getFirstName());
        assertEquals("Ghita", student.getLastName());
        assertEquals("2000-05-15", student.getDateOfBirth());
        assertEquals(24, student.getAge());
    }

    @Test
    void testInvalidFirstName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Student("", "Ognean", "2000-05-15", "male", "12345"));
        assertEquals("First name cannot be empty.", exception.getMessage());
    }

    @Test
    void testInvalidLastName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Student("Petru", "", "2000-05-15", "male", "12345"));
        assertEquals("Last name cannot be empty.", exception.getMessage());
    }

    @Test
    void testInvalidDateFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Student("Olaru", "Codrut", "15-05-2000", "male", "12345"));
        assertEquals("Invalid format. Use yyyy-MM-dd.", exception.getMessage());
    }

    @Test
    void testInvalidGender() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Student("Solomon", "Bogdan", "2000-05-15", "unknown", "12345"));
        assertEquals("Gender must be 'male', 'female'", exception.getMessage());
    }

    @Test
    void testInvalidId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Student("Mircea", "Almasan", "2000-05-15", "male", ""));
        assertEquals("ID cannot be empty.", exception.getMessage());
    }

    @BeforeEach
    void setUpStudentEvaluation() {
        StudentEvaluation.addStudent("Andreea", "Popescu", "1998-10-10", "female", "A001");
        StudentEvaluation.addStudent("Vasile", "Ionescu", "2001-06-20", "male", "B002");
    }

    @Test
    void testAddStudent() {
        assertDoesNotThrow(() -> StudentEvaluation.addStudent("David", "Ciuca", "1995-03-25", "male", "C003"));
    }

    @Test
    void testDeleteStudent() {
        StudentEvaluation.deleteStudent("A001");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> StudentEvaluation.deleteStudent("A001"));
        assertEquals("Student with ID A001 does not exist.", exception.getMessage());
    }

    @Test
    void testRetrieveStudentsByAge() {
        List<Student> students = StudentEvaluation.retrieveStudentsByAge(27);
        students.add(new Student("Andreea", "Popescu", "1998-10-10", "female", "A001"));
        assertEquals(1, students.size());
        assertEquals("Andreea", students.get(0).getFirstName());
    }

    @Test
    void testListStudentsByLastName() {
        List<Student> students = StudentEvaluation.listStudents("LastName");
        assertEquals("Ionescu", students.get(0).getLastName());
        assertEquals("Popescu", students.get(1).getLastName());
    }

    @Test
    void testListStudentsByBirthDate() {
        List<Student> students = StudentEvaluation.listStudents("BirthDate");
        assertEquals("1995-03-25", students.get(0).getDateOfBirth());

    }
}

