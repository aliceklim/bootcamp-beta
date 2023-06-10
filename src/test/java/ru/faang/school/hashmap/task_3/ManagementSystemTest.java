package ru.faang.school.hashmap.task_3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManagementSystemTest {

    private ManagementSystem managementSystem;
    private Student existingStudent;
    private Student nonExistingStudent;

    @BeforeEach
    void setUp(){
        managementSystem = new ManagementSystem();
        existingStudent = new Student("Nome Nome", "Math", 3);
        nonExistingStudent = new Student("New student", "Biology", 2);
        managementSystem.addStudent(new Student("Name Surname", "Biology", 2));
        managementSystem.addStudent(new Student("Nome Cognome", "Biology", 2));
        managementSystem.addStudent(new Student("Nome Nome", "Math", 3));
        managementSystem.addStudent(new Student("Old student", "Math", 3));
    }

    @Test
    void addNonExistingStudentTest(){
        System.out.println(managementSystem.getStudentMapSize());
        int expectedStudentMapSize = 2;
        int expectedStudentListSize = 3;
        String result = managementSystem.addStudent(nonExistingStudent);

        assertEquals(String.format(Message.STUDENT_ADDED, nonExistingStudent.getName()), result);
        assertEquals(expectedStudentMapSize, managementSystem.getStudentMapSize());
        assertEquals(expectedStudentListSize,
                managementSystem.getStudentListSize(nonExistingStudent.getFaculty(), nonExistingStudent.getYear()));
    }

    @Test
    void addExistingStudentTest(){
        int expectedStudentMapSize = 2;
        int expectedStudentListSize = 2;
        StudentException exception = assertThrows(StudentException.class,
                () -> managementSystem.addStudent(existingStudent));

        assertEquals(String.format(Message.STUDENT_EXISTS_ALREADY, existingStudent.getName()), exception.getMessage());
        assertEquals(expectedStudentMapSize, managementSystem.getStudentMapSize());
        assertEquals(expectedStudentListSize,
                managementSystem.getStudentListSize(existingStudent.getFaculty(), existingStudent.getYear()));
    }

    @Test
    void deleteExistingStudentTest(){
        int expectedStudentMapSize = 2;
        int expectedStudentListSize = 1;

        String result = managementSystem.deleteStudent(existingStudent);
        assertEquals(String.format(Message.STUDENT_DELETED, existingStudent.getName()), result);
        assertEquals(expectedStudentListSize,
                managementSystem.getStudentListSize(existingStudent.getFaculty(), existingStudent.getYear()));
        assertEquals(expectedStudentMapSize, managementSystem.getStudentMapSize());
    }

    @Test
    void deleteNonExistingStudentTest(){
        int expectedStudentMapSize = 2;
        int expectedStudentListSize = 2;
        StudentException exception = assertThrows(StudentException.class,
                () -> managementSystem.deleteStudent(nonExistingStudent));

        assertEquals(String.format(Message.STUDENT_DOESNT_EXIST, nonExistingStudent.getName()), exception.getMessage());
        assertEquals(expectedStudentListSize,
                managementSystem.getStudentListSize(nonExistingStudent.getFaculty(), nonExistingStudent.getYear()));
        assertEquals(expectedStudentMapSize, managementSystem.getStudentMapSize());
    }

}
