package alura.forohub.domain.course;

import alura.forohub.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity(name = "Course")
@Table(name = "courses")
@EqualsAndHashCode(of = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private CourseCategory categoria;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Topic> topics;

    public Course(RegisterCourseData registerCourseData){
        this.nombre = registerCourseData.nombre().toUpperCase();
        this.categoria = CourseCategory.valueOf(registerCourseData.categoria().toUpperCase());
    }

    public Course(String courseName){

    }

    public Course(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CourseCategory getCategoria() {
        return categoria;
    }

    public void setCategoria(CourseCategory categoria) {
        this.categoria = categoria;
    }

    public void updateData(UpdateCourseData updateCourseData){
        if(updateCourseData.nombre() != null){
            this.nombre = updateCourseData.nombre();
        }
        if(updateCourseData.categoria() != null){
            this.categoria = CourseCategory.valueOf(updateCourseData.categoria().toUpperCase());
        }
    }

}
