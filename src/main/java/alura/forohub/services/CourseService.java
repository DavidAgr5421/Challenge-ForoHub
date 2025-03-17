package alura.forohub.services;

import alura.forohub.domain.course.*;
import alura.forohub.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public ResponseEntity<ResponseCourseData> registerCourse(RegisterCourseData registerCourseData, UriComponentsBuilder uriComponentsBuilder){
        Course course = repository.save(new Course(registerCourseData));
        ResponseCourseData responseCourseData = new ResponseCourseData(course);
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(url).body(responseCourseData);
    }

    public ResponseEntity<Page<ResponseCourseData>> getCoursesList(Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable).map(ResponseCourseData::new));
    }
    
    public ResponseEntity<ResponseCourseData> getCourseById(Long id){
        return ResponseEntity.ok(new ResponseCourseData(repository.getReferenceById(id)));
    }

    public ResponseEntity<ResponseCourseData> updateCourse(UpdateCourseData updateCourseData, Long id){
        Optional<Course> optionalCourse = repository.findById(id);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            course.updateData(updateCourseData);
            return ResponseEntity.ok(new ResponseCourseData(course));
        }else{
            throw new IllegalArgumentException("El curso con el ID : |" + id + "| no existe.");
        }
    }

    public ResponseEntity<ResponseCourseData> deleteCourse(Long id){
        Optional<Course> optionalCourse = repository.findById(id);
        if(optionalCourse.isPresent()){
            ResponseCourseData responseCourseData = new ResponseCourseData(optionalCourse.get());
            repository.delete(optionalCourse.get());
            return ResponseEntity.ok(responseCourseData);
        }else{
            throw new IllegalArgumentException("El tópico con el ID : |" + id + "| no existe.");
        }
    }

    public ResponseEntity<Page<ResponseCourseData>> searchByCategory(Pageable pageable, String categoria){
        CourseCategory courseCategory = CourseCategory.valueOf(categoria.toUpperCase());
        return ResponseEntity.ok(repository.findByCategoria(pageable,courseCategory).map(ResponseCourseData::new));
    }
}
