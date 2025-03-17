package alura.forohub.domain.course;

public record ResponseCourseData(
        Long id,
        String nombre,
        CourseCategory categoria
) {
    public ResponseCourseData(Course course){
        this(course.getId(), course.getNombre(), course.getCategoria());
    }
}
