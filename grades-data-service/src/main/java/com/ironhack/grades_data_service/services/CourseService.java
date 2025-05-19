package com.ironhack.grades_data_service.services;

import com.ironhack.grades_data_service.DTOS.CourseGradeDTO;
import com.ironhack.grades_data_service.models.Course;
import com.ironhack.grades_data_service.models.Grade;
import com.ironhack.grades_data_service.repositories.CourseRepository;
import com.ironhack.grades_data_service.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;


    /**
     * Retrieves a course by its course code.
     *
     * @param courseCode the unique identifier of the course
     * @return the Course entity if found, or null if no course exists with the given code
     */
    public Course getCourseByCode(Long courseCode) {
        return courseRepository.findById(courseCode).orElse(null);
    }

    /**
     * Retrieves the grades for a course by its code and returns them in a CourseGradeDTO.
     *
     * If the course does not exist, returns {@code null}. Otherwise, returns a CourseGradeDTO containing the course name and its associated grades.
     *
     * @param courseCode the unique code identifying the course
     * @return a CourseGradeDTO with the course name and list of grades, or {@code null} if the course is not found
     */
    public CourseGradeDTO getCourseGrade(Long courseCode) {
        Course course = getCourseByCode(courseCode);
        if (course == null){
            return null;
        }
        List<Grade> grades = gradeRepository.findByCourse_CourseCode(courseCode);
        return new CourseGradeDTO(course.getCourseName(), grades);
    }

}