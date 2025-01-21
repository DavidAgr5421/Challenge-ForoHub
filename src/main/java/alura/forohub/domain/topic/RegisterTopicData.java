package alura.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record RegisterTopicData(
        @NotBlank(message = "El titulo es obligatorio.")
        String titulo,
        @NotBlank(message = "El mensaje es obligatorio.")
        String mensaje,
        @NotBlank(message = "El autor es obligatorio.")
        String autor,
        @NotBlank(message = "El curso es obligatorio.")
        String curso
) {
}
