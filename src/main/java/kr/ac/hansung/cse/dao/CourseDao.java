package kr.ac.hansung.cse.dao;

import kr.ac.hansung.cse.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseDao {

    private JdbcTemplate jdbcTemplate;  // psa(portable service abstraction, sql(x) api

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Course> getCourse(int year, int semester) {

        String sqlStatement= "select * from courses WHERE year = ? AND semester = ?";
        return jdbcTemplate.query(sqlStatement, new Object[]{year, semester}, new RowMapper<Course>() {

            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

                Course course= new Course();

                course.setId(rs.getInt("id"));
                course.setYear(rs.getInt("year"));
                course.setSemester(rs.getInt("semester"));
                course.setCode(rs.getString("code"));
                course.setCredit(rs.getInt("credit"));
                course.setSubject(rs.getString("subject"));
                course.setProfessor(rs.getString("professor"));
                course.setType(rs.getString("type"));

                return course;
            }
        });
    }

    public int totalCredits(){
        String sqlStatement= "SELECT SUM(credit) AS total_credit FROM courses WHERE year != 2024 OR semester != 2";
        return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
    }

    public List<Course> getCourses() {

        String sqlStatement= "SELECT * FROM courses WHERE year != 2024 OR semester != 2";
        return jdbcTemplate.query(sqlStatement, new RowMapper<Course>() {

            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

                Course course= new Course();

                course.setId(rs.getInt("id"));
                course.setYear(rs.getInt("year"));
                course.setSemester(rs.getInt("semester"));
                course.setCode(rs.getString("code"));
                course.setCredit(rs.getInt("credit"));
                course.setSubject(rs.getString("subject"));
                course.setProfessor(rs.getString("professor"));
                course.setType(rs.getString("type"));

                return course;
            }
        });
    }

    public Map<String, Integer> getCourseTotalCredit() {
        String sqlStatement = "SELECT year, semester, SUM(credit) AS total_credit FROM courses WHERE NOT (year = 2024 AND semester = 2) GROUP BY year, semester";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sqlStatement);

        Map<String, Integer> totalCreditMap = new HashMap<>();
        for (Map<String, Object> row : results) {
            int totalCredit = ((Number) row.get("total_credit")).intValue();
            String key = row.get("year") + "-" + row.get("semester");
            totalCreditMap.put(key, totalCredit);
        }

        return totalCreditMap;
    }

    // Crud method
    public boolean insert(Course course) {


        int id= course.getId();
        //year, semester, type, subject, credit, professor, code
        int year=24;

        int semester=2;

        String subject=course.getSubject();

        String professor=course.getProfessor();

        String type= course.getType();

        int credit=course.getCredit();

        String code=course.getCode();

        String sqlStatement= "insert into courses (id, year, semester, code, subject, type, professor, credit) values (?,?,?,?,?,?,?,?)";

        return (jdbcTemplate.update(sqlStatement, new Object[] {id, year, semester, code, subject, type, professor, credit}) == 1);
    }

    // crUd method
    public boolean update(Course course) {

        int id= course.getId();
        //year, semester, type, subject, credit, professor, code
        int year=course.getYear();

        int semester=course.getYear();

        String subject=course.getSubject();

        String professor=course.getProfessor();

        String type= course.getType();

        int credit = course.getCredit();

        String code=course.getCode();

        String sqlStatement= "update courses set id=?, year=?, semester=?, code=?, subject=?, type=?, professor=?, credit=? where id=?";

        return (jdbcTemplate.update(sqlStatement, new Object[] {id, year, semester, code, subject, type, professor, credit}) == 1);
    }

    //cruD method
    public boolean delete(int id) {
        String sqlStatement= "delete from courses where id=?";
        return (jdbcTemplate.update(sqlStatement, new Object[] {id}) == 1);
    }

}
