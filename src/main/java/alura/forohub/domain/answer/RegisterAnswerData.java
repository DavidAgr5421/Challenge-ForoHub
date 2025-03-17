package alura.forohub.domain.answer;

import jakarta.validation.constraints.NotBlank;

public record RegisterAnswerData(
        @NotBlank
        String mensaje,
        Long respuesta_padre_id
) {
}
