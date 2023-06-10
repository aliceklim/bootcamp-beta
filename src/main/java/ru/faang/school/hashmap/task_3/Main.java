package ru.faang.school.hashmap.task_3;

public class Main {
    public static void main(String[] args) {
        ManagementSystem mn = new ManagementSystem();
        mn.addStudent(new Student("Name Surname", "Biology", 2000));
        mn.addStudent(new Student("Nome Cognome", "Biology", 2000));
        mn.addStudent(new Student("Nome Nome", "Math", 2000));
        mn.addStudent(new Student("Name Name", "Math", 2000));
        mn.getStudentsByYearAndFaculty("Math", 2000);
        mn.getAllStudentsGroupedByYearAndFaculty();
    }
}
