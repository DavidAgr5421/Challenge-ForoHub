package alura.forohub.domain.topic;

import java.time.LocalDateTime;

public record ListTopicData(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion
) {
    public ListTopicData(Topic topic){
        this(topic.getId(), topic.getTitulo(), topic.getMensaje(), topic.getFechaCreacion());
    }
}
