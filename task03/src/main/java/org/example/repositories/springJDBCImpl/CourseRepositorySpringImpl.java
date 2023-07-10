package org.example.repositories.springJDBCImpl;

import org.example.models.Course;
import org.example.repositories.CourseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CourseRepositorySpringImpl implements CourseRepository {
    private static final String COURSE_TABLE = "course";
    private final static String SQL_SELECT_ALL = "select * from course";
    private final SimpleJdbcInsert insertCourse;
    private final JdbcTemplate jdbcTemplate;

    public CourseRepositorySpringImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertCourse = new SimpleJdbcInsert(dataSource);

        insertCourse.withTableName(COURSE_TABLE)
                .usingGeneratedKeyColumns("id");
    }

    private static final Function<Course, Map<String, Object>> toParam = course -> {
        Map<String, Object> params = new HashMap<>();

        params.put("title", course.getTitle());
        params.put("start_date", course.getStartDate());
        params.put("finish_date", course.getFinishDate());

        return params;
    };

    private static final RowMapper<Course> toCourse = (row, rowNum) -> Course.builder()
            .id(row.getInt("id"))
            .title(row.getString("title"))
            .startDate(row.getDate("start_date").toLocalDate())
            .finishDate(row.getDate("finish_date").toLocalDate())
            .build();
    @Override
    public void save(Course model) {
        insertCourse.execute(toParam.apply(model));
    }

    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, toCourse);
    }

    @Override
    public void update(Course model) {

    }
}
