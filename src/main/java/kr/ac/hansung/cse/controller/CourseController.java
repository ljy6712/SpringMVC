package kr.ac.hansung.cse.controller;

import kr.ac.hansung.cse.model.Course;
import kr.ac.hansung.cse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {

    // Controller -> Service -> Dao
    @Autowired
    private CourseService courseService;

    @GetMapping("/course")
    public String showCourse(Model model) {
        List<Course> courses = courseService.getCourse(2024, 2);
        model.addAttribute("id_courses", courses);

        return "courses";
    }

    @GetMapping("/createcourse")
    public String createCourse(Model model) {

        model.addAttribute("course", new Course());

        return "createcourse";
    }

    @GetMapping("/courses/credit")
    public String showCourseTotalCredit(Model model) {
        Map<String, Integer> totalCredit = courseService.getCourseTotalCredit();
        int totalCredits = courseService.getTotalCredits();
        model.addAttribute("id_credits", totalCredit);
        model.addAttribute("id_totalCredits", totalCredits);

        return "totalCredit";
    }

    @GetMapping("/courses/credit/detail")
    public String showCourseTotalCreditDetail(Model model, @RequestParam int year, @RequestParam int semester ) {
        List<Course> courses = courseService.getCourse(year, semester);
        model.addAttribute("id_courses", courses);

        return "courses";
    }

    @PostMapping("/docreate")
    public String doCreate(Model model, @Valid Course course, BindingResult result) {

        // System.out.println(course);
        if(result.hasErrors()) {
            System.out.println("== Form data does not validated ==");

            List<ObjectError> errors = result.getAllErrors();

            for(ObjectError error:errors) {
                System.out.println(error.getDefaultMessage());
            }

            return "createcourse";
        }

        // Controller -> Service -> Dao
        courseService.insert(course);

        return "coursecreated";
    }
}
