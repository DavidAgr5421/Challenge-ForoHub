package alura.forohub.domain.topic;

import alura.forohub.domain.course.Course;

import java.time.LocalDateTime;

public record ResponseTopicData(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        Course curso,
        Boolean status,
        LocalDateTime fechaCreacion
) {
    public ResponseTopicData(Topic topic){
        this(topic.getId(), topic.getTitulo(), topic.getMensaje(), topic.getAutor(), topic.getCurso(), topic.getStatus(), topic.getFechaCreacion());
    }
}
