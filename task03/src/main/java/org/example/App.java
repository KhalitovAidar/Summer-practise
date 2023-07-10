package org.example;

import org.example.models.Course;
import org.example.models.Student;
import org.example.repositories.CourseRepository;
import org.example.repositories.StudentRepository;
import org.example.repositories.impl.CourseRepositoryImpl;
import org.example.repositories.impl.StudentRepositoryImpl;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        StudentRepository studentsRepository = new StudentRepositoryImpl();
        CourseRepository courseRepository = new CourseRepositoryImpl();

        Student student = Student.builder()
                .firstName("Имя1")
                .lastName("Фамилия1")
                .age(25)
                .build();

        Course course = Course.builder()
                .title("Java")
                .startDate(LocalDate.parse("2023-12-03"))
                .finishDate(LocalDate.parse("2023-12-20"))
                .build();

        System.out.println(student);
        System.out.println(course);

        studentsRepository.save(student);
        courseRepository.save(course);

        System.out.println(studentsRepository.findAll());
        System.out.println(courseRepository.findAll());
    }
}