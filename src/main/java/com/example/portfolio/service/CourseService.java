package com.example.portfolio.service;

import com.example.portfolio.model.Course;
import com.example.portfolio.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

   
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    
    public Optional<Course> updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id).map(existingCourse -> {
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setProvider(updatedCourse.getProvider());
            existingCourse.setCompletionDate(updatedCourse.getCompletionDate());
            return courseRepository.save(existingCourse);
        });
    }

   
    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
