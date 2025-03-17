package alura.forohub.domain.course;

import jakarta.validation.constraints.NotBlank;

public record RegisterCourseData(
        @NotBlank(message = "El nombre es obligatorio.")
        String nombre,
        @NotBlank(message = "La categoria es obligatoria.")
        String categoria
) {
}
