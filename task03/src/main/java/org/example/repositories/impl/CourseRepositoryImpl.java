package org.example.repositories.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.models.Course;
import org.example.repositories.CourseRepository;
import org.example.repositories.springJDBCImpl.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRepositoryImpl implements CourseRepository {
    private Connection connection = DBUtil.getConnection();
    @Override
    public void save(Course model) {
        String SQL = "INSERT INTO course(title, start_date, finish_date) VALUES (?, ?, ?)";
        Date startDate = Date.valueOf(model.getStartDate());
        Date finishDate = Date.valueOf(model.getFinishDate());
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, model.getTitle());
            statement.setDate(2, startDate);
            statement.setDate(3, finishDate);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Course> findAll() {
        String SQL = "SELECT * FROM course";
        List<Course> courses = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                LocalDate finishDate = resultSet.getDate("finish_date").toLocalDate();

                Course course = Course.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .startDate(startDate)
                        .finishDate(finishDate)
                        .build();

                courses.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public void update(Course model) {
        String SQL = "UPDATE course SET title=?, start_date=?, finish_date=?" +
                "WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, model.getTitle());
            statement.setDate(2, Date.valueOf(model.getStartDate()));
            statement.setDate(3, Date.valueOf(model.getFinishDate()));
            statement.setInt(4, model.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
