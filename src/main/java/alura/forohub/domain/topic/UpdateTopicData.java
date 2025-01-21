package alura.forohub.domain.topic;

import java.time.LocalDateTime;

public record UpdateTopicData(
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
    public UpdateTopicData(Topic topic){
        this(topic.getTitulo(), topic.getMensaje(), topic.getAutor(), topic.getCurso());
    }
}
