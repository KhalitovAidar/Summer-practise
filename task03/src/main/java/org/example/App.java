package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.models.Course;
import org.example.models.Student;
import org.example.repositories.CourseRepository;
import org.example.repositories.StudentRepository;
import org.example.repositories.impl.DBConfig;
import org.example.repositories.springJDBCImpl.CourseRepositorySpringImpl;
import org.example.repositories.springJDBCImpl.StudentRepositorySpringImpl;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;


public class App
{
    public static void main( String[] args ) throws FileNotFoundException {
        // Default JDBC implementations test

//        StudentRepository studentsRepository = new StudentRepositoryImpl();
//        CourseRepository courseRepository = new CourseRepositoryImpl();
//
//        Student student = Student.builder()
//                .firstName("Имя1")
//                .lastName("Фамилия1")
//                .age(25)
//                .build();
//
//        Course course = Course.builder()
//                .title("Java")
//                .startDate(LocalDate.parse("2023-12-03"))
//                .finishDate(LocalDate.parse("2023-12-20"))
//                .build();
//
//        System.out.println(student);
//        System.out.println(course);
//
//        studentsRepository.save(student);
//        courseRepository.save(course);
//
//        System.out.println(studentsRepository.findAll());
//        System.out.println(courseRepository.findAll());

        // Spring JDBC tests
        DataSource dataSource = new DBConfig().getDataSource();
        StudentRepository studentRepository = new StudentRepositorySpringImpl(dataSource);
        CourseRepository courseRepository = new CourseRepositorySpringImpl(dataSource);

        Student student = Student.builder()
                .firstName("Имя2")
                .lastName("Фамилия2")
                .age(33)
                .build();

        Course course = Course.builder()
                .title("English")
                .startDate(LocalDate.parse("2019-01-09"))
                .finishDate(LocalDate.parse("2020-01-09"))
                .build();

        System.out.println(student);
        studentRepository.save(student);
        System.out.println(student);

        System.out.println(course);
        courseRepository.save(course);
        System.out.println(course);

        System.out.println(studentRepository.findAll());

        System.out.println(courseRepository.findAll());
    }
}