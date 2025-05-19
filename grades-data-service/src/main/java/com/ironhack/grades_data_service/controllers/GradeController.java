package com.ironhack.grades_data_service.controllers;

import com.ironhack.grades_data_service.models.Grade;
import com.ironhack.grades_data_service.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;

    /**
     * Retrieves all grade records.
     *
     * @return HTTP 200 OK response containing a list of all grades
     */
    @GetMapping
    public ResponseEntity<?> getAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        return ResponseEntity.ok(grades);
    }

    /**
     * Retrieves a grade by its unique identifier.
     *
     * @param id the ID of the grade to retrieve
     * @return HTTP 200 with the grade if found, or HTTP 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getGradeById(@PathVariable Long id) {
        return gradeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

