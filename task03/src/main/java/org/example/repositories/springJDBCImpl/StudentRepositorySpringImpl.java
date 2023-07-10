package org.example.repositories.springJDBCImpl;

import org.example.models.Student;
import org.example.repositories.StudentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCallOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StudentRepositorySpringImpl implements StudentRepository {
    private static final String STUDENT_TABLE = "student";
    private final static String SQL_SELECT_ALL = "select * from student";
    private final DataSource dataSource;
    private final SimpleJdbcInsert insertStudent;
    private final JdbcTemplate jdbcTemplate;

    public StudentRepositorySpringImpl(DataSource dataSource) {
        this.dataSource = dataSource;

        jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertStudent = new SimpleJdbcInsert(dataSource);

        insertStudent.withTableName(STUDENT_TABLE)
                .usingGeneratedKeyColumns("id");
    }

    public StudentRepositorySpringImpl(DataSource dataSource, SimpleJdbcInsert insertStudent, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.insertStudent = insertStudent;
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final Function<Student, Map<String, Object>> toParams = student -> {
        Map<String, Object> params = new HashMap<>();

        params.put("first_name", student.getFirstName());
        params.put("last_name", student.getLastName());
        params.put("age", student.getAge());

        return params;
    };

    private static final RowMapper<Student> toStudent = (row, rowNumber) -> Student.builder()
            .id(row.getInt("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();


    @Override
    public void save(Student model) {
        insertStudent.execute(toParams.apply(model));
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, toStudent);
    }

    @Override
    public void update(Student model) {

    }
}
