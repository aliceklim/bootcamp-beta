package ru.faang.school.hashmap.task_3;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ManagementSystem {

    private final Map<String, List<Student>> studentsMap = new HashMap<>();

    public String addStudent(Student student){
        String key = student.getFaculty() + student.getYear();

        if (studentsMap.containsKey(key)) {
            List<Student> students = studentsMap.get(key);
            if (students.contains(student)){
                throw new StudentException(String.format(Message.STUDENT_EXISTS_ALREADY, student.getName()));
            }
            students.add(student);
        } else {
            List<Student> students = new ArrayList<>();
            students.add(student);
            studentsMap.put(key, students);
        }

        return String.format(Message.STUDENT_ADDED, student.getName());
    }

    public String deleteStudent(Student student){
        String key = student.getFaculty() + student.getYear();
        List<Student> students = studentsMap.get(key);
        if (!students.contains(student)){
            throw new StudentException(String.format(Message.STUDENT_DOESNT_EXIST, student.getName()));
        }
        students.remove(student);
        return String.format(Message.STUDENT_DELETED, student.getName());
    }

    public void getStudentsByYearAndFaculty(String faculty, int year){
        String key = faculty + year;
        List<Student> students;

        if (studentsMap.isEmpty()){
            throw new StudentException(Message.STUDENTS_LIST_EMPTY);
        } else if (!studentsMap.containsKey(key)) {
            throw new StudentException(Message.NO_SUCH_COURSE);
        } else {
            students = studentsMap.get(key);
            System.out.println(students);
        }
    }

    public int getStudentMapSize(){
        return studentsMap.size();
    }

    public int getStudentListSize(String faculty, int year){
        String key = faculty + year;
        List<Student> students;
        if (!studentsMap.containsKey(key)){
            throw new StudentException(Message.NO_SUCH_COURSE);
        }
        return studentsMap.get(key).size();
    }

    public void getAllStudentsGroupedByYearAndFaculty(){
        for(Map.Entry<String, List<Student>> entry : studentsMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
