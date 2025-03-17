package alura.forohub.repository;

import alura.forohub.domain.course.Course;
import alura.forohub.domain.course.CourseCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAll(Pageable pageable);

    Optional<Course> findByNombre(String nombre);

    Page<Course> findByCategoria(Pageable pageable, CourseCategory categoria);
}
