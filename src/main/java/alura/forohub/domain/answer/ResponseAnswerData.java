package alura.forohub.domain.answer;

import java.time.LocalDateTime;

public record ResponseAnswerData(
        Long id,
        String mensaje,
        Long topico_id,
        LocalDateTime fecha_creacion,
        Long autor_id,
        Long respuesta_padre_id
) {

    public ResponseAnswerData(Answer answer){
        this(answer.getId(), answer.getMensaje(), answer.getTopic().getId(), answer.getFechaCreacion(), answer.getUser().GetId(), answer.getRespuestaPadreId());
    }
}
