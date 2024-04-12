package kr.ac.hansung.cse.service;

import kr.ac.hansung.cse.dao.CourseDao;
import kr.ac.hansung.cse.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

@Service
public class CourseService {

    //service -> dao
    @Autowired
    private CourseDao courseDao;

    public List<Course> getAllCourses() {
        return courseDao.getCourses();
    }

    public Map<String, java.lang.Integer> getCourseTotalCredit(int year, int semester){
        return courseDao.getCourseTotalCredit(year, semester);
    }

    public List<Course> getCourse(int year, int semester){
        return courseDao.getCourse(year, semester);
    }

    public int getTotalCredits(){
        return courseDao.totalCredits();
    }
    public void insert(Course course) {
        courseDao.insert(course);
    }
}
