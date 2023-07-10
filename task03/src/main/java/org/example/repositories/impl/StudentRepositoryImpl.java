package org.example.repositories.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.models.Student;
import org.example.repositories.springJDBCImpl.DBUtil;
import org.example.repositories.StudentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    private Connection connection = DBUtil.getConnection();
    public void save(Student model) {
        try {
            String SQL = "INSERT INTO student(first_name, last_name, age) VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getLastName());
            statement.setInt(3, model.getAge());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM student";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Student student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .build();

                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public void update(Student model) {
        String SQL = "UPDATE course SET first_name=?, last_name=?, age=?" +
                "WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getLastName());
            statement.setInt(3, model.getAge());
            statement.setInt(4, model.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
