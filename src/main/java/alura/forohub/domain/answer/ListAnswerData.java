package alura.forohub.domain.answer;

import java.time.LocalDateTime;

public record ListAnswerData(
        Long id,
        String mensaje,
        LocalDateTime fecha_creacion,
        Long autor_id
) {
    public ListAnswerData(Answer answer){
        this(answer.getId(), answer.getMensaje(), answer.getFechaCreacion(), answer.getUser().GetId());
    }
}
