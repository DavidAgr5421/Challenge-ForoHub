package alura.forohub.controller;

import alura.forohub.domain.course.RegisterCourseData;
import alura.forohub.domain.course.ResponseCourseData;
import alura.forohub.domain.course.UpdateCourseData;
import alura.forohub.services.CourseService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    public ResponseEntity<ResponseCourseData> registerCourse(@RequestBody @Valid RegisterCourseData registerCourseData, UriComponentsBuilder uriComponentsBuilder){
        return service.registerCourse(registerCourseData,uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseCourseData>> getCoursesList(Pageable pageable){
        return service.getCoursesList(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCourseData> getCourseById(@PathVariable Long id){
        return service.getCourseById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ResponseCourseData>> searchCourseByCategory(Pageable pageable,@RequestParam String categoria){
        return service.searchByCategory(pageable,categoria);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseCourseData> updateCourse(@RequestBody @Valid UpdateCourseData updateCourseData, @PathVariable Long id){
        return service.updateCourse(updateCourseData,id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseCourseData> deleteCourse(@PathVariable Long id){
        return deleteCourse(id);
    }
}
